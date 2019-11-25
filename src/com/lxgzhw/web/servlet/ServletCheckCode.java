package com.lxgzhw.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/ServletCheckCode")
public class ServletCheckCode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //-   2.1 生成验证码图片对象
        int width = 100;
        int height = 50;
        BufferedImage codeImg =
                new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //-   2.2 根据图片对象获取画笔,设置背景色,边框
        Graphics pen = codeImg.getGraphics();
        pen.setColor(Color.BLACK);
        pen.fillRect(0, 0, width, height);
        pen.setColor(Color.RED);
        pen.drawRect(0, 0, width - 1, height - 1);

        //-   2.3 随机生成验证码,通过画字符串的方法
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";
        Random r = new Random();
        int len = str.length();
        pen.setColor(Color.WHITE);
        pen.setFont(new Font("Helvetica", Font.BOLD, 24));
        StringBuffer serverCheckCode = new StringBuffer();

        for (int i = 0; i < 4; i++) {
            int charIndex = r.nextInt(len);
            char c = str.charAt(charIndex);
            //记录下来
            serverCheckCode.append(c);
            pen.drawString(c + "", width / 5 * i + 15, height / 2 + 10);
        }

        //将验证码存储进session
        String serverCheckCodeStr = serverCheckCode.toString();
        HttpSession session = request.getSession();
        session.setAttribute("serverCheckCode", serverCheckCodeStr);

        //-   2.4 随机生成干扰线,通过画线的方法
        pen.setColor(Color.PINK);
        for (int i = 0; i < 20; i++) {
            int x1 = r.nextInt(width);
            int x2 = r.nextInt(width);
            int y1 = r.nextInt(height);
            int y2 = r.nextInt(height);
            pen.drawLine(x1, y1, x2, y2);
        }

        //-   2.5 将验证码图片返回给浏览器
        ImageIO.write(
                codeImg, "jpg", response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
