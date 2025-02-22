package zoho;

import java.util.Scanner;

public class reservationUI
{
  static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) 
	{
		reservation_system ref=new reservation_system();
		boolean b=true;
		do {
			System.out.println("\t\t\twelcome to IRTC booking\n\t\t----------------------------------------");
			System.out.println("\t\t\t1.book ticket\n\t\t\t2.cancel ticket\n\t\t\t3.print bookedtickets\n\t\t\t4.veiw avalible ticket\n\t\t\t5.exit");
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			int select=sc.nextInt();
			switch (select)
			{
			case 1: {
				    sc.nextLine();
				    ref.bookTicket();
				    break;
			        }
			case 2:{
				    ref.cancel_ticket();
				    break;
			       }
            case 3: {
                     ref.print_bookedTicket();
            	     break;
			        }
			case 4:{
				     ref.check_avaliableticket();
				     break;	
			      }
			case 5: 
			     {System.out.println("\t\t\t\"THANKYOU FOR USING IRTC\"");
				   b=false;
				   break;
			    }
			default:
			      System.out.println("please enter valid input for better service");
		 }
			
		} while (b);

	}
	
}
