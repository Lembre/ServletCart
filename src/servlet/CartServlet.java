package servlet;

import dao.ItemsDAO;
import entity.Cart;
import entity.Items;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CartServlet extends HttpServlet {
    private String action;//��ʾ���ﳵ�Ķ���,add,show delete
    private ItemsDAO idao= new ItemsDAO() ;//Ҫ�õ�����Ʒҵ���߼���Ķ���,���ݱ�Ż�ȡ��Ʒ

    public CartServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        if(req.getParameter("action" )!= null){//�ж��ύ�Ķ��������Ƿ�Ϊ��
            this.action= req.getParameter("action");//��ȡ��action
            if(action.equals("add" )){//����������Ʒ�����ﳵ
               if(addToCart(req,resp))
                {
                   req.getRequestDispatcher("/success.jsp").forward(req,resp);
                }else {
                   req.getRequestDispatcher("/failure.jsp").forward(req,resp);
               }

            }
            if(action.equals("show")){//�������ʾ���ﳵ
                req.getRequestDispatcher("/cart.jsp").forward(req,resp);
            }
            if(action.equals("delete")){
                if(deleteFromCart(req,resp)){
                    req.getRequestDispatcher("/cart.jsp").forward(req,resp);
                }
                else
                {
                    req.getRequestDispatcher("/cart.jsp").forward(req,resp);
                }
            }
        }
    }

    //�����Ʒ�����ﳵ�ķ���
    private boolean addToCart(HttpServletRequest req, HttpServletResponse resp){
        String id= req.getParameter("id" );//��Ϊ�������Ƿ������ڲ���ת�������id��num������󴫵ݣ���successҳ����Խ���
        String number= req.getParameter("num" );
        Items item= idao.getItemsById(Integer.parseInt(id));
        //�Ƿ��ǵ�һ�θ����ﳵ�����Ʒ����Ȼ��Ҫ��session�д���һ���µĹ��ﳵ����
        if (req.getSession().getAttribute("cart")==null){
            Cart cart= new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        Cart cart= (Cart)req.getSession().getAttribute("cart");//�����Ϊ����ôֱ�ӻ��������ﳵ����������ü���else
        if (cart.addGoodsInCart(item,Integer.parseInt(number))){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean deleteFromCart(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("id");
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        Items item = idao.getItemsById(Integer.parseInt(id));
        if(cart.removeGoodsFormCart(item)){
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}
