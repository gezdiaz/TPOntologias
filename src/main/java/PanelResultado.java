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
import javax.swing.JTable;

public class PanelResultado extends JPanel {

    JFrame ventana;
    JTable tablaResultado;
    TablaResultadoModel tablaResultadoModel;
    JButton btnVolver;

    public PanelResultado(JFrame ventana, ArrayList<Object> resultados){

        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        this.ventana = ventana;

        JLabel lblResultados = new JLabel("Resultados");
        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridheight = 1;
        cons.gridwidth = 2;
        cons.insets = new Insets(40, 20, 40, 20);
        cons.anchor = GridBagConstraints.CENTER;
        lblResultados.setFont(new Font(lblResultados.getFont().getName(), lblResultados.getFont().getStyle(), 30));
        add(lblResultados, cons);

        tablaResultadoModel = new TablaResultadoModel(resultados);
        tablaResultado = new JTable(tablaResultadoModel);
        cons.gridx = 0;
        cons.gridy = 1;
        cons.gridheight = 1;
        cons.gridwidth = 2;
        cons.insets = new Insets(40, 20, 40, 20);
        cons.anchor = GridBagConstraints.CENTER;
        add(tablaResultado, cons);

        btnVolver = new JButton("Volver");
        cons.gridx = 0;
        cons.gridy = 2;
        cons.gridheight = 1;
        cons.gridwidth = 2;
        cons.insets = new Insets(40, 20, 40, 20);
        cons.anchor = GridBagConstraints.CENTER;
        add(btnVolver, cons);
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelResultado.this.ventana.setContentPane(new MainPanel(PanelResultado.this.ventana));
                PanelResultado.this.ventana.revalidate();
                PanelResultado.this.ventana.repaint();
                PanelResultado.this.ventana.pack();
                PanelResultado.this.ventana.setLocationRelativeTo(null);
                PanelResultado.this.ventana.requestFocus();
            }
        });

    }
}
