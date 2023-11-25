import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

public class ConversionGui extends JFrame implements ActionListener
{
    private JButton convertBtn;
    private JToggleButton encodebtn;
    private JButton reset;
    private JTextArea inputArea;
    static JTextArea outputArea;
    private JScrollPane inputPane;
    private JScrollPane outputPane;
    static JLabel label;
    private boolean isEncode;
    private JCheckBox biBox;
    

    public ConversionGui()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        label = new JLabel();
        label.setBounds(0, 97, 800, 30);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setForeground(new Color(0xff0000));
        label.setFont(new Font("Arial", Font.BOLD, 11));

        JLabel instruction = new JLabel("<html>This is a Binary converter.<br/>You can choose whether to encode or decode by clicking the button labeled \"Encode\" or \"Decode\". The color around it will change either (Green - Encode : Red - Decode). <br/>You will type your 'input' on the first text box and will get the 'output' in the second below text box by clicking the \"Convert\" button on the right.<br/>You can erase your input and output by clicking the \"Reset\" button below the Convert button.<br/><br/>You are able to type the values from the ASCII Table or the character(s), it represents decimal number from 0 to 255 inclusive, as an input.</html>");
        instruction.setBounds(100, 0, 680, 100);

        instruction.setVerticalAlignment(JLabel.CENTER);
        instruction.setHorizontalAlignment(JLabel.CENTER);
        instruction.setHorizontalTextPosition(JLabel.CENTER);
        instruction.setVerticalTextPosition(JLabel.CENTER);

        instruction.setFont(new Font("Arial", Font.ITALIC, 9));

        JLabel background = new JLabel();

        convertBtn = new JButton("Convert");
        convertBtn.addActionListener(this);
        convertBtn.setBounds(675, 250, 100, 100);

        encodebtn = new JToggleButton("Encode");
        encodebtn.addActionListener(this);
        encodebtn.setFont(new Font("Arial", Font.PLAIN, 18));
        encodebtn.setBounds(25, 280, 100, 100);
        encodebtn.setOpaque(true);
        encodebtn.setBackground(Color.GREEN);

        reset = new JButton("Reset");
        reset.addActionListener(this);
        reset.setBounds(675, 350, 100, 50);
        
        inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        inputPane = new JScrollPane(inputArea);
        inputPane.setBounds(150, 130, 500, 200);
        inputPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(inputPane);

        outputPane = new JScrollPane(outputArea);
        outputPane.setBounds(150, 340, 500, 200);
        outputPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(outputPane);
        
        this.add(convertBtn);
        this.add(encodebtn);
        this.add(reset);
        this.add(label);
        this.add(instruction);
        this.add(background);

        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        
        if (encodebtn.isSelected())
        {
            encodebtn.setText("Decode");
            encodebtn.setBackground(Color.RED);
            isEncode = true;
        }
        else
        {
            encodebtn.setBackground(Color.GREEN);
            encodebtn.setText("Encode");
            isEncode = false;
        }

        if (e.getSource() == convertBtn)
        {
            outputArea.setText(null);
            label.setText(null);
            Converter.Binary(inputArea.getText(), isEncode);
        }

        if (e.getSource() == reset)
        {
            inputArea.setText(null);
            outputArea.setText(null);
            label.setText(null);
        }
    }
}
