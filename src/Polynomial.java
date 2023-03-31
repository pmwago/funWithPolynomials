import java.util.*;
class Polynomial{
    public static class Node{ int coef, power;
        Node next;
        public Node(int coef, int pow){
            this.coef = coef;
            this.power = pow;
            next= null;
        }
    }
    public static class MyLinkedList{
        Node first, last;
        public MyLinkedList(){
            first= null;
            last= null;
        }
    }
    public static void printlist(Node temp1,Node temp2){
        System.out.println("Entered polynomials are :");
        System.out.print(" "+temp1.coef+"x^"+temp1.power+" ");
        temp1 = temp1.next;
        while(temp1 != null){
            if(temp1.coef < 0)
                System.out.print(" "+temp1.coef+"x^"+temp1.power+" ");
            else
                System.out.print(" +"+temp1.coef+"x^"+temp1.power+" ");
            temp1 = temp1.next;
        }
        System.out.println("");
        System.out.print(" "+temp2.coef+"x^"+temp2.power+" ");
        temp2 = temp2.next;
        while(temp2 != null){
            if(temp2.coef < 0)
                System.out.print(" "+temp2.coef+"x^"+temp2.power+" ");
            else
                System.out.print(" +"+temp2.coef+"x^"+temp2.power+" ");
            temp2 = temp2.next;
        }
    }

    public static Node singleAdd( Node head ) {
        Node tempOne = null, temptwo = null;

        while ( head != null ) {
            Node temp = head.next;
            boolean f = true;

            while( temp != null ) {
                if( head.power == temp.power ) {
                    temp.coef = temp.coef + head.coef;
                    f = false;
                    break;
                }
                temp = temp.next;
            }

            if ( f ) {
                if ( tempOne == null ) {
                    tempOne = new Node( head.coef, head.power );
                    temptwo = tempOne;
                } else {
                    tempOne.next = new Node( head.coef, head.power );
                    tempOne = tempOne.next;
                }
            }

            head = head.next;
        }
        return temptwo;
    }

    public static Node getLast( Node head1 ) {
        while ( head1.next != null ) head1 = head1.next;
        return head1;
    }

    public static void polyadd(Node head1 , Node head2){
        Node tempOne = singleAdd( head1 );
        getLast( tempOne ).next = singleAdd( head2 );
        Node newTemp = singleAdd( tempOne );

        while ( newTemp != null ) {
            if ( newTemp.coef < 0 )
                System.out.print( " "+newTemp.coef+"x^"+newTemp.power+" " );
            else
                System.out.print( " +"+newTemp.coef+"x^"+newTemp.power+" " );
            newTemp = newTemp.next;
        }
    }

    public static void polymul(Node head1 , Node head2){
        Node tempOne = null, t = null;
        while(head1 != null){
            Node temp2 = head2;
            while(temp2 != null){
                if ( tempOne == null ){
                    tempOne = new Node( ( head1.coef * temp2.coef ), ( head1.power + temp2.power ) );
                    t = tempOne;
                } else {
                    tempOne.next = new Node( ( head1.coef * temp2.coef ), ( head1.power + temp2.power ) );
                    tempOne = tempOne.next;
                }
                temp2 = temp2.next;
            }
            head1 = head1.next;
        }
        Node unusedNull = null;
        polyadd( t, null);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the coefficient and power of the first polynomial. Type 0 at the end: ");
        Node ll1 = new Node(-1, -1);
        Node headll1 = ll1;
        while(true){
            int co = sc.nextInt();
            if(co == 0)break;
            int po = sc.nextInt();
            Node node = new Node(co,po);
            ll1.next = node;
            ll1 = ll1.next;
        }
        // ll1 = ll1.next;
        System.out.println("Enter the coefficient and power of the second polynomial. Type 0 at the end: ");
        // // MyLinkedList ll = new MyLinkedList();
        Node ll2 = new Node(-1, -1);
        Node headll2 = ll2;
        while(true){
            int co = sc.nextInt();
            if(co == 0)break;
            int po = sc.nextInt();
            Node node = new Node(co,po);
            ll2.next = node;
            ll2 = ll2.next;
        }
        System.out.println("The entered polynomials are:");
        printlist(headll1.next, headll2.next);
        System.out.println("");
        while(true){
            System.out.println("Which task you want to do?");
            System.out.println("1- Add");
            System.out.println("2- Multiply");
            System.out.println("3- Print Polynomials :");
            System.out.println("4- Delete :");
            System.out.println("5- Exit :");

            System.out.println("Please enter a digit (1-5): ");
            int n = sc.nextInt();
            if(n == 1){
                polyadd(headll1.next,headll2.next);
                System.out.println(" ------------------------- ");
            }
            else if(n == 2){
                polymul(headll1.next,headll2.next);
                System.out.println(" ------------------------- ");
            }
            else if(n == 3){
                printlist(headll1.next, headll2.next);
                System.out.println(" ------------------------- ");
            }
            else if(n == 4){
                System.out.println("Which power you want to be deleted from both polynomials:");
                // delete(headll1.next,headll2.next);
                int pow = sc.nextInt();
                Node head1 = headll1,head2 = headll2;
                while(head1.next != null){
                    if(head1.next.power == pow){
                        head1.next = head1.next.next;
                    }

                    head1 = head1.next;
                }
                while(head2.next != null){
                    if(head2.next.power == pow){
                        head2.next = head2.next.next;
                    }
                    head2 = head2.next;
                }

            }
            else{
                System.exit(0);
            }
        }
    }
}
