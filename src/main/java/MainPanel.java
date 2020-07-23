import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainPanel extends JPanel {

    JTextArea lblDescripcion;
    JLabel lblMaterias, lblPromedio, lblTitulo;
    JTextField txtMaterias, txtPromedio;
    JButton btnAceptar, btnCancelar;


    public MainPanel(){

        setLayout(new GridBagLayout());
        GridBagConstraints cons= new GridBagConstraints();

        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        lblTitulo = new JLabel("SysBeca");
        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridheight = 1;
        cons.gridwidth = 2;
        cons.insets = new Insets(40, 20, 40, 20);
        cons.anchor = GridBagConstraints.CENTER;
        add(lblTitulo, cons);

        lblDescripcion = new JTextArea("Para poder evaluar las solicitudes de los postulantes requerimos que se ingresen los criterios con los cuales se determinarán aquellos habilitados a conseguir la beca.");
        cons.gridx = 0;
        cons.gridy = 1;
        cons.gridheight = 1;
        cons.gridwidth = 2;
        cons.insets = new Insets(40, 20, 40, 20);
        cons.anchor = GridBagConstraints.CENTER;
        add(lblDescripcion, cons);

        lblMaterias = new JLabel("Cantidad minima de materias aprobadas");
        cons.gridx = 0;
        cons.gridy = 2;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.insets = new Insets(40, 20, 40, 20);
        cons.anchor = GridBagConstraints.CENTER;
        add(lblMaterias, cons);

        lblPromedio= new JLabel("Promedio minimo");
        cons.gridx = 0;
        cons.gridy = 3;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.insets = new Insets(40, 20, 40, 20);
        cons.anchor = GridBagConstraints.CENTER;
        add(lblPromedio, cons);

        txtMaterias = new JTextField();
        cons.gridx = 1;
        cons.gridy = 2;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.insets = new Insets(40, 20, 40, 20);
        cons.anchor = GridBagConstraints.CENTER;
        cons.fill = GridBagConstraints.HORIZONTAL;
        txtMaterias.setPreferredSize(btnAceptar.getPreferredSize());
        add(txtMaterias, cons);

        txtPromedio = new JTextField();
        cons.gridx = 1;
        cons.gridy = 3;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.insets = new Insets(40, 20, 40, 20);
        cons.anchor = GridBagConstraints.CENTER;
        cons.fill = GridBagConstraints.HORIZONTAL;
        txtPromedio.setPreferredSize(btnAceptar.getPreferredSize());
        add(txtPromedio, cons);

        cons.gridx = 0;
        cons.gridy = 4;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.insets = new Insets(40, 20, 40, 20);
        cons.anchor = GridBagConstraints.CENTER;
        cons.fill = GridBagConstraints.HORIZONTAL;
        add(btnAceptar, cons);

        cons.gridx = 1;
        cons.gridy = 4;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.insets = new Insets(40, 20, 40, 20);
        cons.anchor = GridBagConstraints.CENTER;
        cons.fill = GridBagConstraints.HORIZONTAL;
        add(btnCancelar, cons);

    }

}
