import java.util.*;

class Product {
    public String name;
    public double price;
    public int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String toString() {
        return "Product [name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
    }
}
class Queue {
    Product Q[];
    int F = -1;
    int R = -1;

    int n;

    Queue(int n) {
        this.n = n;
        Q = new Product[n];
    }

    void enque(Product X) {
        if (R >= (n - 1)) {
            System.out.println("Queue Overflow");
        } else {
            R++;
            Q[R] = X;
            if (F == -1) {
                F = 0;
            }
        }
    }

    void deque() {
        if (F == -1) {
            System.out.println("Queue Underflow");
        }
        else {
            Product Y;
            Y = Q[F];
            if (F == R) {
                F = R = -1;
            }
            else {
                F++;
            }
            System.out.println("Deleted Product = "+Y.name);
        }
    }

    void display() {
        if (F == -1) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Cart:");
            for (int i = F; i <= R; i++) {
                double pricewithgst;
                System.out.println("||----------------------------------------------------------- Bill NO :- "+(i+1)+" -----------------------------------------------------------||");
                System.out.println("Product ID             ||             Name of Product             ||             Price             ||             quantity             ||             GST BILL");
                System.out.println("||--------------------------------------------------------------------------------------------------------------------------------------------||");
                System.out.print(i+1+"           ");
                System.out.print("Product name: " + Q[i].name+"           ");
                System.out.print("Product price: " + Q[i].price+"           ");
                System.out.print("Product quantity: " + Q[i].quantity+"           ");
                pricewithgst=(Q[i].price*Q[i].quantity)+((Q[i].price*Q[i].quantity)*0.18);
                System.out.println("price to pay with gst - "+pricewithgst);
                System.out.println("||---------------------------------------------------------------------------------------------------------------------------------------------||");
            }
        }
    }

    void addDiscount(double discount,String productName)
    {
        double cal_support=discount/100;
        for(int i=F;i<=R;i++)
        {
            if(productName.equalsIgnoreCase(Q[i].name))
            {
                Q[i].price=Q[i].price-(Q[i].price*cal_support);
                System.out.println("new price is -->"+Q[i].price);
            }
            else
            {
                //do nothing
            }
        }
    }
    void finalBill()
    {
        double total_bill=0 ;
        if(R==-1)
        {
            System.out.println("you didn't buy anything");
            System.out.println();
        }
        for(int i=F;i<=R;i++)
        {

            double pricewithgst = (Q[i].price*Q[i].quantity)+((Q[i].price*Q[i].quantity)*0.18);
            total_bill=total_bill+pricewithgst;
        }
        System.out.println("||-----------------------------------------------------------||");
        System.out.println(" total amount you need to pay is ---> "+total_bill);
        System.out.println("||-----------------------------------------------------------||");
    }

}

class ProductBillingSystem {
    static Scanner sc = new Scanner(System.in);

    static void add(Queue cart) {
        System.out.println("Enter product name: ");
        String productName = sc.next();
        System.out.println("Enter product price: ");
        double productPrice = sc.nextDouble();
        System.out.println("Enter product quantity: ");
        int productQuantity = sc.nextInt();
        Product newProduct = new Product(productName, productPrice, productQuantity);
        cart.enque(newProduct);
        System.out.println("Product added successfully.");
    }

    public static void main(String[] args) {
        Queue cart = new Queue(1024);

        while (true) {
            System.out.println("Enter 1 for adding ");
            System.out.println("Enter 2 to remove");
            System.out.println("Enter 3 to get total amount and final bill");
            System.out.println("Enter 4 for giving discount on some specific item");
            System.out.println("Enter 5 to display cart");
            System.out.println("Enter 6 for exit");
            System.out.print("Select an option: ");
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1 :
                    add(cart);
                    break;

                case 2:
                    cart.deque();
                    break;

                case 3:
                    cart.finalBill();
                    break;

                case 4:
                    System.out.println("enter product name to give discount on it");
                    String productName=sc.next();
                    System.out.println("enter discount percentage");
                    double discount=sc.nextDouble();
                    if(discount< 51 && discount>0)
                    {
                        cart.addDiscount(discount,productName);
                    }
                    else
                    {
                        System.out.println("invalid discount");
                        continue;
                    }
                    break;

                case 5:
                    cart.display();
                    break;

                case 6:
                    System.out.println("Thankyou For Shopping...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    continue;

            }
        }
    }
}