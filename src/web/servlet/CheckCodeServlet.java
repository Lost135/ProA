package web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 50;
        //创建图片对象
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        //填充图片背景色
        Graphics g = image.getGraphics();//画笔对象
        g.setColor(Color.cyan);
        g.fillRect(0,0,width, height);//填充颜色
        //画边框
        g.setColor(Color.BLACK);
        g.drawRect(0,0,width - 1,height - 1);
        //生成随机字符
        String str = "ABCDEFGHIJKLMNOPQRESUVWXYZ0123456789";
        Random ran = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 4 ; i++) {
            //生成随机字符
            int index = ran.nextInt(str.length());
            char ch = str.charAt(index);
            //写字符
            sb.append(ch);
            g.drawString(ch + "",width/5*i,height/2);
        }
        //将验证码存入session
        String check_session = sb.toString();
        request.getSession().setAttribute("CHECKCODE_SERVER",check_session);

        //画干扰线
        g.setColor(Color.darkGray);
        for (int i = 0; i < 7; i++) {
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);
            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }
        //输出图片
        ImageIO.write(image,"jpg",response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
