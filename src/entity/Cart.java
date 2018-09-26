package entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//���ﳵ��
public class Cart {
    //������Ʒ�ļ��ϣ�����JavaSE�������ּ���Set,HashSet,���С�ͨ������������ﳵ�Ĺ�����Ʒ�ļ��ϸ�����һ�ּ�ֵ�ԵĹ�ϵ
    private HashMap<Items, Integer> goods;

    //���ﳵ�ܽ��
    private  double totalPrice;

    //���췽��
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

    //�����Ʒ�����ﳵ�ķ���,���� ������Ʒ�Ķ��������
    public boolean addGoodsInCart(Items items, int number)
    {
        //���Ϊ�棬��ʾ�Ѿ�����������Ʒ
        if (goods.containsKey(items))
        {
            goods.put(items, goods.get(items) + number);//�ϲ�����
        }
        else
        {
            goods.put(items, number);
        }

        //���¼��㹺�ﳵ�ܽ��
        calTotalPrice();

        //��ʾ��ӳɹ�
        return true;
    }

    //ɾ����Ʒ�ķ���
    public boolean removeGoodsFormCart(Items item){
        goods.remove(item);
        calTotalPrice();
        return true;
    }

    //ͳ�ƹ��ﳵ���ܽ��
    public double calTotalPrice()
    {
        double sum = 0.0;

        //��ü��ļ���
        Set<Items> keys = goods.keySet();

        //��õ���������
        Iterator<Items> it = keys.iterator();

        while (it.hasNext())
        {
            Items i = it.next();
            sum += i.getPrice()* goods.get(i);
        }

        //���ù��ﳵ���ܽ��
        this.setTotalPrice(sum);
        return this.getTotalPrice();
    }

    //���Թ��ﳵ��
    public static void main(String[] args)
    {
        Items i1 = new Items(1,"��������Ь","����",200,500,"001.png");
        Items i2 = new Items(2,"�����˶�Ь","����",300,500,"002.png");
        Items i3 = new Items(1,"��������Ь","����",200,500,"001.jpg");

        Cart c = new Cart();
        c.addGoodsInCart(i1,1);
        c.addGoodsInCart(i2,2);

        /*�ٴι�����������Ь������3˫,��ʱtoString����ͬ����Ʒ��û�кϲ���һ�����⣺����ڹ��ﳵ�б�֤��������ͬ��Ʒ�ļ�¼
        ���Ǻϲ�����дequals()��hashCode()����,������������֮��������û�ϲ��ٸ�дaddGoodsInCart()����*/
        c.addGoodsInCart(i3, 3);

        //�����ܲ��ܱ���������Ʒ�ļ��ϣ���������ֱ��,entrySet�õ�����һ����ֵ�Լ��ϵĶ���
        Set<Map.Entry<Items, Integer>> items = c.getGoods().entrySet();

        //Set�Ĵ��������Map.Entry<Items, Integer>
        for (Map.Entry<Items, Integer> obj : items)
        {
            //���Ǵ�ӡ�������ǹ�ϣ����������Ҫ��дtoString����
            System.out.println(obj);
        }

        System.out.println("���ﳵ�ܽ��:" + c.getTotalPrice());
    }
}
/*ͨ����дObject��hashCode ��equals���������޸������¶���ʱ�Ĺ�����������ҵĹ�����ô��new�����������������һ������
��ô����new��������ͬ����Ḳ��ǰ��������ɵ�ͬһ���󡣴�ʱ���޸�add��������ʵ�ֿ�����һ����*/