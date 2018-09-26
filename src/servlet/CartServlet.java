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
    private String action;//表示购物车的动作,add,show delete
    private ItemsDAO idao= new ItemsDAO() ;//要用到的商品业务逻辑类的对象,根据编号获取商品

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
        if(req.getParameter("action" )!= null){//判断提交的动作类型是否为空
            this.action= req.getParameter("action");//获取该action
            if(action.equals("add" )){//如果是添加商品进购物车
               if(addToCart(req,resp))
                {
                   req.getRequestDispatcher("/success.jsp").forward(req,resp);
                }else {
                   req.getRequestDispatcher("/failure.jsp").forward(req,resp);
               }

            }
            if(action.equals("show")){//如果是显示购物车
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

    //添加商品进购物车的方法
    private boolean addToCart(HttpServletRequest req, HttpServletResponse resp){
        String id= req.getParameter("id" );//因为在上面是服务器内部跳转所以这个id和num可以向后传递，即success页面可以接收
        String number= req.getParameter("num" );
        Items item= idao.getItemsById(Integer.parseInt(id));
        //是否是第一次给购物车添加商品，显然需要给session中创建一个新的购物车对象
        if (req.getSession().getAttribute("cart")==null){
            Cart cart= new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        Cart cart= (Cart)req.getSession().getAttribute("cart");//如果不为空那么直接获得这个购物车对象，这里最好加上else
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
