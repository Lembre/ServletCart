package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
    //���ݿ�����
    private static final String driver = "com.mysql.jdbc.Driver";

    //�������ݿ��URL��ַ
    private static final String url="jdbc:mysql:///shopping?useUnicode=true&characterEncoding=UTF-8";

    //���ݿ���û���
    private static final String username="root";

    //���ݿ������
    private static final String password="123456";

    private static Connection conn=null;

    //��̬����鸺���������
    static
    {
        try
        {
            //ע�ᵽDriverManager��,��ע������
            Class.forName(driver);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    //����ģʽ�������ݿ����Ӷ���
    public static Connection getConnection() throws Exception
    {
        if(conn==null)
        {
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        }
        return conn;
    }

    public static void main(String[] args) {

        try
        {
            Connection conn = DBHelper.getConnection();
            if(conn!=null)
            {
                System.out.println("���ݿ�����������");
            }
            else
            {
                System.out.println("���ݿ������쳣��");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }
}