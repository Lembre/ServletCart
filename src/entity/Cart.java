package entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//购物车类
public class Cart {
    //购买商品的集合，但在JavaSE中有三种集合Set,HashSet,序列。通过分析这个购物车的购买商品的集合更像是一种键值对的关系
    private HashMap<Items, Integer> goods;

    //购物车总金额
    private  double totalPrice;

    //构造方法
    public Cart()
    {
        goods = new HashMap<Items, Integer>();
        totalPrice = 0.0;
    }

    public HashMap<Items, Integer> getGoods()
    {
        return goods;
    }

    public void setGoods(HashMap<Items, Integer> goods)
    {
        this.goods = goods;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //添加商品进购物车的方法,参数 购买商品的对象和数量
    public boolean addGoodsInCart(Items items, int number)
    {
        //如果为真，表示已经购买过这个商品
        if (goods.containsKey(items))
        {
            goods.put(items, goods.get(items) + number);//合并数量
        }
        else
        {
            goods.put(items, number);
        }

        //重新计算购物车总金额
        calTotalPrice();

        //表示添加成功
        return true;
    }

    //删除商品的方法
    public boolean removeGoodsFormCart(Items item){
        goods.remove(item);
        calTotalPrice();
        return true;
    }

    //统计购物车的总金额
    public double calTotalPrice()
    {
        double sum = 0.0;

        //获得键的集合
        Set<Items> keys = goods.keySet();

        //获得迭代器对象
        Iterator<Items> it = keys.iterator();

        while (it.hasNext())
        {
            Items i = it.next();
            sum += i.getPrice()* goods.get(i);
        }

        //设置购物车的总金额
        this.setTotalPrice(sum);
        return this.getTotalPrice();
    }

    //测试购物车类
    public static void main(String[] args)
    {
        Items i1 = new Items(1,"沃特篮球鞋","温州",200,500,"001.png");
        Items i2 = new Items(2,"李宁运动鞋","广州",300,500,"002.png");
        Items i3 = new Items(1,"沃特篮球鞋","温州",200,500,"001.jpg");

        Cart c = new Cart();
        c.addGoodsInCart(i1,1);
        c.addGoodsInCart(i2,2);

        /*再次购买沃特篮球鞋，购买3双,此时toString中相同的商品并没有合并在一起，问题：如何在购物车中保证不出现相同商品的记录
        而是合并？重写equals()和hashCode()方法,解决了这个问题之后发现数量没合并再改写addGoodsInCart()方法*/
        c.addGoodsInCart(i3, 3);

        //测试能不能遍历购物商品的集合，这样更加直观,entrySet得到的是一个键值对集合的对象
        Set<Map.Entry<Items, Integer>> items = c.getGoods().entrySet();

        //Set的存放类型是Map.Entry<Items, Integer>
        for (Map.Entry<Items, Integer> obj : items)
        {
            //但是打印出来的是哈希吗不形象，所以要重写toString方法
            System.out.println(obj);
        }

        System.out.println("购物车总金额:" + c.getTotalPrice());
    }
}
/*通过重写Object类hashCode 和equals方法，来修改生成新对象时的规则，如果符合我的规则，那么你new出来的两个对象就是一个对象，
那么后面new出来的相同对象会覆盖前面的已生成的同一对象。此时再修改add方法即可实现看起来一样。*/