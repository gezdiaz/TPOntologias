import java.awt.Dimension;
import java.awt.Font;
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
        cons.insets = new Insets(10, 20, 20, 20);
        cons.anchor = GridBagConstraints.CENTER;
        lblTitulo.setFont(new Font(lblTitulo.getFont().getName(), lblTitulo.getFont().getStyle(), 50));
        add(lblTitulo, cons);

        lblDescripcion = new JTextArea("Para poder evaluar las solicitudes de los postulantes\nrequerimos que se ingresen los criterios con los cuales se\ndeterminar\u00e1n aquellos habilitados a conseguir la beca.");
        cons.gridx = 0;
        cons.gridy = 1;
        cons.gridheight = 1;
        cons.gridwidth = 2;
        cons.insets = new Insets(10, 20, 10, 20);
        cons.anchor = GridBagConstraints.CENTER;
        lblDescripcion.setPreferredSize(new Dimension(350, 50));
        lblDescripcion.setRows(3);
        lblDescripcion.setEditable(false);
        add(lblDescripcion, cons);

        lblMaterias = new JLabel("Cantidad minima de materias aprobadas");
        cons.gridx = 0;
        cons.gridy = 2;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.insets = new Insets(10, 20, 10, 5);
        cons.anchor = GridBagConstraints.WEST;
        add(lblMaterias, cons);

        lblPromedio= new JLabel("Promedio minimo");
        cons.gridx = 0;
        cons.gridy = 3;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.insets = new Insets(10, 20, 10, 5);
        cons.anchor = GridBagConstraints.WEST;
        add(lblPromedio, cons);

        txtMaterias = new JTextField();
        cons.gridx = 1;
        cons.gridy = 2;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.insets = new Insets(5, 20, 10, 20);
        cons.anchor = GridBagConstraints.CENTER;
        cons.fill = GridBagConstraints.NONE;
        txtMaterias.setPreferredSize(btnCancelar.getPreferredSize());
        txtMaterias.setText("2");
        add(txtMaterias, cons);

        txtPromedio = new JTextField();
        cons.gridx = 1;
        cons.gridy = 3;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.insets = new Insets(5, 20, 10, 20);
        cons.anchor = GridBagConstraints.CENTER;
        cons.fill = GridBagConstraints.NONE;
        txtPromedio.setPreferredSize(btnCancelar.getPreferredSize());
        txtPromedio.setText("5");
        add(txtPromedio, cons);

        cons.gridx = 0;
        cons.gridy = 4;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.insets = new Insets(10, 20, 10, 20);
        cons.anchor = GridBagConstraints.CENTER;
        cons.fill = GridBagConstraints.NONE;
        add(btnAceptar, cons);
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO hacer la consulta
//                ArrayList<Alumno> regulares = new ArrayList<>();
//                for(int i=0; i<50; i++){
//                    Alumno a = new Alumno(23330+i, 20+i, "Alumno "+String.valueOf(i), "Sistemas", "Regular", (50.0+2*i)/i, 20.65*(5.6/i)+i, i, 10+i);
//                    regulares.add(a);
//                }
                if(txtMaterias.getText().isEmpty()){
                    //Tiene que haber ingresado algo
                    System.out.println("No ingresó materias aprobadas");
                    return;
                }
                if(txtPromedio.getText().isEmpty()){
                    //Tiene que haber ingresado algo
                    System.out.println("No ingresó promedio");
                    return;
                }
                int cantMaterias;
                double promedioMin;
                try{
                    cantMaterias = Integer.parseInt(txtMaterias.getText());
                    promedioMin = Double.parseDouble(txtPromedio.getText());
                }catch (Exception ex){
                    //Ingresó algo incorrecto
                    System.out.println("Valores ingresados: Materias: '"+txtMaterias.getText()+"' Promedio: '"+txtPromedio.getText()+"'");
                    return;
                }
                ArrayList<Alumno> regulares = (ArrayList<Alumno>) Main.getListaAlumnosBecadosRegulares();
                ArrayList<Alumno> filtrado = new ArrayList<>();
                for(Alumno a : regulares){
                    if(a.getPromedio()>=promedioMin && a.getUltimasMaterias()>=cantMaterias){
                        filtrado.add(a);
                    }
                }
                ArrayList<Alumno> ingresantes = (ArrayList<Alumno>) Main.getListaAlumnosBecadosIngresantes();
                filtrado.addAll(ingresantes);
                MainPanel.this.ventana.setContentPane(new PanelResultado(MainPanel.this.ventana, filtrado));
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
        cons.insets = new Insets(10, 20, 10, 20);
        cons.anchor = GridBagConstraints.CENTER;
        cons.fill = GridBagConstraints.NONE;
        add(btnCancelar, cons);

    }

}
