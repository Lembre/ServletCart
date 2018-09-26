package entity;

//商品类
public class Items {

    private int id; // 商品编号
    private String name; // 商品名称
    private String city; // 产地
    private int price; // 价格
    private int number; // 库存
    private String picture; // 商品图片

    //保留此不带参数的构造方法
    public Items()
    {

    }

    public Items(int id,String name,String city,int price,int number,String picture)
    {
        this.id = id;
        this.name = name;
        this.city = city;
        this.picture = picture;
        this.price = price;
        this.number = number;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }



    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return this.getId()+this.getName().hashCode();//相同编号，相同字符串对应的hashCode相同
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if(this==obj)//当前对象等于传进来的对象
        {
            return true;//相同对象直接返回true
        }
        if(obj instanceof Items)//是不是同一个类
        {
            Items i = (Items)obj;//传进来的对象是Object类型，所以要强制转换为商品类
            if(this.getId()==i.getId()&&this.getName().equals(i.getName()))
            {
                return true;//内容相同，return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public String toString()
    {
        return "商品编号："+this.getId()+",商品名称："+this.getName();
    }
}
