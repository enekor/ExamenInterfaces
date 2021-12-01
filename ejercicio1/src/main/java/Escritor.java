import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.event.*;

public class Escritor extends JFrame {
    private JPanel principal,zonaTexto,zonaBotones,zonaBotonesI,zonaBotonesD;

    private JButton copiar, pegar, deshacer,salir;
    private JLabel lineas
    ,portapapeles;
    private JTextArea escritura;

    private UndoManager um;
    private int numLineas=1;

    Escritor(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
        listeners();
    }

    private void init(){


        escritura.setFocusTraversalPolicyProvider(true);
        um = new UndoManager();

        copiar.setText("copiar");
        pegar.setText("pegar");
        deshacer.setText("deshacer");
        salir.setText("salir");
        lineas.setText("lineas:");
        portapapeles.setText("portapapales:");

        this.add(principal);

        this.pack();
    }

    private void listeners() {

        escritura.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                um.addEdit(e.getEdit());
            }
        });

        copiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                escritura.copy();
                portapapeles.setText("Portapapeles: \n" +escritura.getSelectedText());
            }
        });

        pegar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                escritura.paste();
            }
        });

        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        deshacer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(um.canUndo()){
                    um.undo();
                }
            }
        });

        escritura.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                numLineas+= escritura.getLineCount();
                lineas.setText("Lineas: "+numLineas);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                numLineas+=escritura.getLineCount();
                lineas.setText("Lineas: "+numLineas);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                numLineas+=escritura.getLineCount();
                lineas.setText("Lineas: "+numLineas);
            }
        });

    }

}
