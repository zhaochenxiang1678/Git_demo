package Body;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame {
    static ArrayList<User> list = new ArrayList<>();
    static {
        list.add(new User("zhangsan", "123"));
        list.add(new User("lisi", "1234"));
    }

    public LoginJFrame() {
        initView();
        this.setVisible(true);
    }

    public void initView() {
        this.setSize(603, 400);
        this.setTitle("登录界面");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);

        // 用户名
        JLabel usernameText = new JLabel(new ImageIcon("D:\\AI大数据寒假训练营\\JigsawVersion2\\图片\\用户名.png"));
        usernameText.setBounds(130, 135, 40, 40);
        this.getContentPane().add(usernameText);

        JTextField username = new JTextField();
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        // 密码
        JLabel passwordText = new JLabel(new ImageIcon("D:\\AI大数据寒假训练营\\JigsawVersion2\\图片\\密码.png"));
        passwordText.setBounds(130, 195, 40, 40);
        this.getContentPane().add(passwordText);

        JPasswordField password = new JPasswordField();
        password.setBounds(195, 194, 200, 30);
        this.getContentPane().add(password);

        // 验证码
        JLabel codeText = new JLabel(new ImageIcon("D:\\AI大数据寒假训练营\\JigsawVersion2\\图片\\验证码.png"));
        codeText.setBounds(133, 256, 40, 40);
        this.getContentPane().add(codeText);

        JTextField code = new JTextField();
        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);

        String codeStr = CodeUtil.getCode();
        JLabel rightCode = new JLabel(codeStr);
        rightCode.setBounds(300, 256, 100, 30);
        this.getContentPane().add(rightCode);

        // 登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setBounds(250, 300, 100, 30);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputUsername = username.getText();
                String inputPassword = new String(password.getPassword());
                String inputCode = code.getText();

                // 验证码校验
                if (!inputCode.equalsIgnoreCase(rightCode.getText())) {
                    JOptionPane.showMessageDialog(LoginJFrame.this, "验证码错误！");
                    return;
                }

                // 用户校验
                boolean isValid = false;
                for (User user : list) {
                    if (user.username.equals(inputUsername) && user.password.equals(inputPassword)) {
                        isValid = true;
                        break;
                    }
                }

                if (isValid) {
                    // 关闭登录界面，打开游戏界面
                    LoginJFrame.this.dispose();
                    new GameJFrame();
                } else {
                    JOptionPane.showMessageDialog(LoginJFrame.this, "用户名或密码错误！");
                }
            }
        });
        this.getContentPane().add(loginButton);

        // 刷新验证码按钮
        JButton refreshCode = new JButton("换一张");
        refreshCode.setBounds(400, 256, 80, 30);
        refreshCode.addActionListener(e -> {
            String newCode = CodeUtil.getCode();
            rightCode.setText(newCode);
        });
        this.getContentPane().add(refreshCode);
    }

}
