package Clases;

import Formularios.FrmQR;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JOptionPane;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Samuel David Ortiz
 */
public class UsuarioClass {

    private String usuario;
    private String codigo;
    private String nombres;
    private String apellidos;
    private String contraseña1;
    private String contraseña2;
    private String fechaNac;
    
    private ImageIcon Img;
    private Icon icono;
    private JLabel label;

    public String claveMurci(String texto) {
        texto = texto.replace('m', '0');
        texto = texto.replace('M', '0');
        texto = texto.replace('u', '1');
        texto = texto.replace('U', '1');
        texto = texto.replace('r', '2');
        texto = texto.replace('R', '2');
        texto = texto.replace('c', '3');
        texto = texto.replace('C', '3');
        texto = texto.replace('i', '4');
        texto = texto.replace('I', '4');
        texto = texto.replace('e', '5');
        texto = texto.replace('E', '5');
        texto = texto.replace('l', '6');
        texto = texto.replace('L', '6');
        texto = texto.replace('a', '7');
        texto = texto.replace('A', '7');
        texto = texto.replace('g', '8');
        texto = texto.replace('G', '8');
        texto = texto.replace('o', '9');
        texto = texto.replace('O', '9');
        return texto;
    }
    
    public UsuarioClass(){
        
    }
    
    public UsuarioClass(JLabel label){
        this.label = label;
    }

    public void nuevoUsuario(String codigo, String nombres, String apellidos, String fechaNac, String usuario,
            String pass1, String pass2) throws WriterException {
        //String usuario, String email, String contraseña1,  String contraseña2){
        
        this.codigo = codigo;
        
        try {
            File file = new File("usuarios.txt");

            FileWriter archivo = new FileWriter(file.getAbsoluteFile(), true);

            String texto = codigo + "," + nombres + "," + apellidos + "," + fechaNac + "," + claveMurci(usuario) + ","
                    + claveMurci(pass1) + "," + claveMurci(pass2);

            PrintWriter imprimir = new PrintWriter(archivo);
            imprimir.println(texto);

            archivo.close();
        } catch (IOException ex) {
            //Logger.getLogger(UsuarioJuego.class.getName()).log(Level.SEVERE, null, ex);
        }

        int dialogButton = JOptionPane.YES_NO_OPTION;

        JOptionPane.showConfirmDialog(null, "Usuario creado exitosamente\nDesea ver el codigo QR", "Usuario creado", dialogButton);

        try {
            QRCodeWriter qrCode = new QRCodeWriter();
            BitMatrix bqr = qrCode.encode(codigo, BarcodeFormat.QR_CODE.QR_CODE, 200, 200);
            Path pQr = FileSystems.getDefault().getPath("./src/Images/QR/" + codigo + ".png");
            MatrixToImageWriter.writeToPath(bqr, "PNG", pQr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }

        if (dialogButton == JOptionPane.YES_OPTION) {
            System.out.println("Mostar codigo QR");
            //paintimages();
            new FrmQR(this.codigo).setVisible(true);
        } else {
            System.out.println("No mostrar QR");
        }

    }
    
    public void paintimages() {
        //URL url = this.getClass().getResource("src/images/" + image + ".png");
        //Img = new ImageIcon(getClass().getResource(image + ".png"));
        Img = new ImageIcon("./src/Images/QR/" + this.codigo + ".png");
        System.out.println("./src/Images/QR/" + this.codigo + ".png");
        icono = new ImageIcon(Img.getImage().getScaledInstance(70, 75,
                Image.SCALE_DEFAULT));
        label.setIcon(icono);
    }

    public boolean validarUsuario(String usuario, String contraseña) {
        String[] u;
        boolean isLogin = false;
        try {
            File myObj = new File("usuarios.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                u = data.split(",");

                if (u[4].equals(claveMurci(usuario)) && u[6].equals(claveMurci(contraseña))) {
                    isLogin = true;
                    this.setNombres(u[2] + " " + u[3]);
                    System.out.println("data = " + this.getNombres());
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return isLogin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContraseña1() {
        return contraseña1;
    }

    public void setContraseña1(String contraseña1) {
        this.contraseña1 = contraseña1;
    }

    public String getContraseña2() {
        return contraseña2;
    }

    public void setContraseña2(String contraseña2) {
        this.contraseña2 = contraseña2;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

}
