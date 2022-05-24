/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.File;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Samuel David Ortiz
 */
public class BuscadorClass {

    private DefaultMutableTreeNode root;

    private DefaultTreeModel treeModel;

    private String ruta;

    public BuscadorClass() {

    }

    public BuscadorClass(String ruta, JTree tree) {
        this.setRuta(ruta);
        System.out.println(this.toString());
    }

    public void arbol(JTree tree) {
        //File fileRoot = new File("C:/");
        File fileRoot = new File(this.getRuta());
        root = new DefaultMutableTreeNode(new FileNode(fileRoot));
        treeModel = new DefaultTreeModel(root);

        tree.setModel(treeModel);
        tree.setShowsRootHandles(true);

        CreateChildNodes ccn
                = new CreateChildNodes(fileRoot, root);
        new Thread(ccn).start();
    }

    public void info(String ruta2, JTextArea texto) {

        File file = new File(ruta2);

        double bytes = Math.round(file.length() * 100.0) / 100.0;
        double kilobytes = Math.round((bytes / 1024) * 100.0) / 100.0;
        double megabytes = Math.round((kilobytes / 1024) * 100.0) / 100.0;

        texto.append("Nombre: " + file.getName() + "\n");
        texto.append("Ubicacion: " + file.getAbsolutePath() + "\n");

        texto.append("Tamaño en bytes : " + bytes + "\n");
        texto.append("Tamaño en kilobytes : " + kilobytes + "\n");
        texto.append("Tamaño en megabytes : " + megabytes + "\n");

        //System.out.println("Size: " + file.length());
    }

    public class CreateChildNodes implements Runnable {

        private DefaultMutableTreeNode root;

        private File fileRoot;

        public CreateChildNodes(File fileRoot,
                DefaultMutableTreeNode root) {
            this.fileRoot = fileRoot;
            this.root = root;
        }

        @Override
        public void run() {
            createChildren(fileRoot, root);
        }

        private void createChildren(File fileRoot,
                DefaultMutableTreeNode node) {
            File[] files = fileRoot.listFiles();
            if (files == null) {
                return;
            }

            for (File file : files) {
                DefaultMutableTreeNode childNode
                        = new DefaultMutableTreeNode(new FileNode(file));
                node.add(childNode);
                if (file.isDirectory()) {
                    createChildren(file, childNode);
                }
            }
        }

    }

    public class FileNode {

        private File file;

        public FileNode(File file) {
            this.file = file;
        }

        @Override
        public String toString() {
            String name = file.getName();
            if (name.equals("")) {
                return file.getAbsolutePath();
            } else {
                return name;
            }
        }
    }

    public String getRuta() {
        return ruta;
    }

    @Override
    public String toString() {
        return "BuscadorClass{" + "ruta=" + ruta + '}';
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}
