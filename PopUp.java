import java.awt.*;
import java.awt.event.*;

public class PopUp extends Frame{
    private Button submit;

    //CONSTRUCTOR
    public PopUp(){
        this.setLayout(new GridLayout(3, 2));

        submit = new Button("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();//cause window to be closed
            }
        });
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                ((Frame)(e.getSource())).dispose();
            }
        });
    }

    //submit listener
    public void addSubmitListener(ActionListener listener){
        submit.addActionListener(listener);
    }

    //make the box visible and interactive
    public void activate(){
        this.add(submit);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
