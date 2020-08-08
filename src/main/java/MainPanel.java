import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    JFrame ventana;


    public MainPanel(JFrame ventana){

        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        this.ventana = ventana;

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
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO hacer la consulta
                ArrayList<Alumno> resultadosPrueba = new ArrayList<>();
                for(int i=0; i<50; i++){
                    Alumno a = new Alumno(23330+i, 20+i, "Alumno "+String.valueOf(i), "Sistemas", 20.65*(5.6/i)+i, 10+2*i, 20+3*i);
                    resultadosPrueba.add(a);
                }
                MainPanel.this.ventana.setContentPane(new PanelResultado(MainPanel.this.ventana, resultadosPrueba));
                MainPanel.this.ventana.revalidate();
                MainPanel.this.ventana.repaint();
                MainPanel.this.ventana.pack();
                MainPanel.this.ventana.setLocationRelativeTo(null);
                MainPanel.this.ventana.requestFocus();
            }
        });

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
