package zoho;

import java.util.Scanner;
import java.util.*;
import java.util.Iterator;
public class reservation_system {

  int total_berth=3,total_ticket=5;
  int upper=1,middle=1,lower=1;//berth
  int RAC=1,waiting_list=1,canceled_ticket=0;
  int booked_ticket=0;
  int confirmed_ticket=0;


  ArrayList<passenger> bookedPassenger   = new ArrayList<passenger>();//to store all booked passenger detials
  ArrayList<passenger> cancelledPassenger= new ArrayList<passenger>();//to store cancelled passenger detials
  ArrayList<passenger> RAC_Passenger     = new ArrayList<passenger>();//to store Rac passenger detials
  ArrayList<passenger> waiting_Passenger = new ArrayList<passenger>();//to store waiting list passenger detials
static Scanner sc=new Scanner(System.in);


public int avaliable_ticket()//to check is their any tickets avalible to book
{
	return (int)total_ticket-(upper+lower+middle+RAC+waiting_list);
}

//to paste remove from here

public void check_availBerth()//to check which berth is avaliable
{
	if(upper>0) {System.out.println("\tupper berth avaliable ticket:"+upper);}
	if(lower>0) {System.out.println("\tmiddle berth avaliable ticket:"+lower);}
	if(middle>0) {System.out.println("\tmiddle berth avaliable ticket:"+middle);}

}

public boolean status(String User_pref)//to check the berth are avaliable
{
	if(User_pref.equalsIgnoreCase("lower"))
	{
	  if(lower>0) 
	   {
		lower--;
		return true;
	   }
	  return false;
	}
	else if(User_pref.equalsIgnoreCase("upper"))
	{ if(upper>0)
	   {
		upper--;
		return true;
	   }
	  return false;
	}
	else if(User_pref.equalsIgnoreCase("middle"))
	{ if(middle>0) 
	   {
		middle--;
		return true;
	   }
	  return false;
	}
	else { System.out.println("invalid berth selection");return false;}
}

public void book_ticket(String User_name,int User_age,String User_gender,String User_berth)//to book ticket
{
		if(status(User_berth))
		{
			passenger store =new passenger(User_name,User_age,User_gender,User_berth);
			bookedPassenger.add(store) ;
			
			booked_ticket++;
			confirmed_ticket++;
			System.out.println("--------------------------------------------------------------------");
			System.out.println("*********"+User_berth+" berth ticket booked Sucessfully*********");
			System.out.println("--------------------------------------------------------------------");
			return;
		}
		else 
		{
			System.out.println("--------------------------------------------------------------------");
			System.out.println("*****Sorry "+User_berth+" berth is not avaliable******");
			System.out.println("--------------------------------------------------------------------");
			System.out.println("\tAvaliable berth are");
			check_availBerth();
		}
	 
}
// paste upto here(optional if hard)


// book tickets
public void bookTicket() //collecting user details to book a ticket
{
if(avaliable_ticket()<5 )
   {
	
	System.out.print("Enter your name:");
	String User_name=sc.nextLine().toUpperCase();
	System.out.print("Enter your age:");
	int User_age=sc.nextInt();
	System.out.print("Enter your gender(m/f):");
	sc.nextLine();
	String User_gender=sc.nextLine().toUpperCase();
	System.out.print("Enter birth preference (upper/lower/middle):");
	String User_berth=sc.nextLine().toLowerCase();
	
	if (total_berth-confirmed_ticket>0) //ticket book start
	{
		
		if(User_age>5)
		{ //pastecode from down in here if above methods are hard
			
			book_ticket(User_name,User_age,User_gender,User_berth);
		}
		else 
		{System.out.println("--------------------------------------------------------------------");
			System.out.println("\t********* Sorry age should be greater than 5 *********");
			System.out.println("--------------------------------------------------------------------");
		}
		
		
	} //ticket book end
	
	else //RAC booking starts here if all berth the are booked
	{
		System.out.println("--------------------------------------------------------------------");
		System.out.println("\t********* Sorry all berth are full you can try booking in RAC ********* \n--------------------------------------------------------------");
		System.out.println("\n\t1.book\n\t2.exit");
		int RAC_select=sc.nextInt();
		
		if(RAC_select==1)//to book RAC start
		{
			if(RAC>0)//booking success start
			{
			passenger store =new passenger(User_name,User_age,User_gender,User_berth);
			bookedPassenger.add(store) ;
			RAC_Passenger.add(store) ;
			System.out.println("--------------------------------------------------------------------");
			System.out.println("********* RAC ticket booked Sucessfully *********");
			System.out.println("--------------------------------------------------------------------");
			RAC--;
			booked_ticket++;
			sc.nextLine();
			}//booking success end
			
			else //"waiting list booking starts" here if all RAC are booked
			{
				System.out.println("--------------------------------------------------------------------");
				System.out.println("\t********* ********* Sorry RAC berth is not avaliable try booking in waiting list *********\n--------------------------------------------------------------------");
				System.out.println("\n\t1.book waiting list ticket\n\t2.exit");
				int wait_select=sc.nextInt();
				if(wait_select==1)//above user select to book waiting list ticket
				{
				if(waiting_list>0)//waiting list bookng success start
				{
					passenger store =new passenger(User_name,User_age,User_gender,User_berth);
					bookedPassenger.add(store) ;
					waiting_Passenger.add(store) ;
					System.out.println("--------------------------------------------------------------------");
					System.out.println("\t********* Waiting list ticket booked Sucessfully *********");
					System.out.println("--------------------------------------------------------------------");
					waiting_list--;
					booked_ticket++;
					sc.nextLine();
				}//waiting list bookng success start
				
				else //if all tickets are booked 
				{System.out.println("--------------------------------------------------------------------");
					System.out.println("\t********* Sorry all the tickets are booked *********");
					System.out.println("--------------------------------------------------------------------");
					return;
				}//if all tickets are booked  end
				}
			
			}//"waiting list booking ends" here if all RAC are booked
			
		}//to book RAC end
		
	  }//RAC booking ends here if all berth the are booked
	}
else 
{
	System.out.println("--------------------------------------------------------------------");
	System.out.println("\t\t\t********* All tickets are booked *********");
	System.out.println("--------------------------------------------------------------------");
}

}//method ends

public void cancel_ticket()
{
	
	if (!(bookedPassenger.isEmpty())) //to check is any booked the ticket
	{
	 System.out.print("Enter your name: ");
	// sc.nextLine();
	 String User_name=sc.nextLine().toUpperCase();
	 System.out.print("Enter berth preference (upper/lower/middle): ");
	 String User_pref=sc.nextLine();
	 passenger canceledticket=null;
	 
		Iterator<passenger> i = bookedPassenger.iterator();//converting array list to iterator because to
		boolean check_passenger=false;                     // remove passengers safely or else  we get CTE 
		while (i.hasNext()) {                              //and index out of bound exception 
	
			passenger Vaule=i.next();
		    if (Vaule.name.equalsIgnoreCase(User_name))
		    { check_passenger=true;
		        confirmed_ticket--;
		        booked_ticket--;
		        canceledticket =Vaule;
		        i.remove();// Safely remove from the list
		        break;
		    }
		    
		} 
		
		if(check_passenger)//check pass detials there that is already booked it will execute
		{
		   if(User_pref.equalsIgnoreCase("upper"))
		   { 
			   upper++;
			   System.out.println("--------------------------------------------------------------------");
			   System.out.println("\t********* Successfully cancelled ticket *********");
			   System.out.println("--------------------------------------------------------------------");
			   cancel_transfer("upper");
		   }
		   if(User_pref.equalsIgnoreCase("lower")) 	
		   {
			   lower++;
			   System.out.println("--------------------------------------------------------------------");
			   System.out.println("\t********* Successfully cancelled ticket *********");
			   System.out.println("--------------------------------------------------------------------");
			   cancel_transfer("lower");
		   }
		   if(User_pref.equalsIgnoreCase("middle")) 
		   {
			   middle++;
			   System.out.println("--------------------------------------------------------------------");
			   System.out.println("\t********* Successfully cancelled ticket *********");
			   System.out.println("--------------------------------------------------------------------");
			   cancel_transfer("middle");
		   }
		   
		}//if block ends check pass detials
		else
		{
			System.out.println("--------------------------------------------------------------------");
			System.out.println("\t\t\t******* No user found invalid data ********");
			System.out.println("--------------------------------------------------------------------");
		}
    } 
   else {
	   System.out.println("--------------------------------------------------------------------");
       System.out.println("\t\t\t******** kindly book tickets to cancel *********");
       System.out.println("--------------------------------------------------------------------");
   }
     
}//method ends here

public void cancel_transfer(String berth)//if user canceled confirmed tickets rac will shift to 
{                                        //confirmed and waiting list will shift to rac
	
     if(RAC_Passenger!=null && !RAC_Passenger.isEmpty())//checking rac is booked 
       {
	
          RAC--;
          confirmed_ticket++;
	      bookedPassenger.add(RAC_Passenger.get(0));
	      RAC_Passenger.remove(0);
	      System.out.println("\t*******Your ticket is filled by RAC ticket ********* ");
	      System.out.println("--------------------------------------------------------------------");
	     if(waiting_Passenger!=null && !waiting_Passenger.isEmpty())//checking waiting list is booked
	      {
		     RAC++;
		     //waiting_Passenger.get(0).User_pref=berth;
		     RAC_Passenger.add(waiting_Passenger.get(0));
		     waiting_Passenger.remove(0);
		     waiting_list++;
	
	       }
	
       }
}
public void print_bookedTicket()//printing booked ticket
{ if(!(bookedPassenger.isEmpty()))
		{
	      for (passenger i:bookedPassenger)
	          {
		       traverse(i);
	          }
         }
else {	     
	System.out.println("--------------------------------------------------------------------");
	System.out.println("\t****** No ticket to Print *******");
    System.out.println("--------------------------------------------------------------------");
}
	
}	
public void traverse(passenger i)//printing booked ticket// using this method we can see waiting list pass det,confirmed pass det
{
	System.out.println("Name: "+i.name);
	System.out.println("Age: "+i.age);
	System.out.println("Gender: "+i.gender);
	System.out.println("birth: "+i.birth);
	System.out.println("---------------------------");
}


public void check_avaliableticket() //checking avaliable ticket
{
     System.out.println("Avaliable tickets: "+(total_berth-confirmed_ticket));
     System.out.println("RAC tickets: "+RAC);
     System.out.println("Waiting List tickets: "+waiting_list);
     System.out.println("--------------------------------------------------------------------");
     System.out.println("\t1.To book tickets press 1 \n\t2.To exit press 2");
     int Tobook=sc.nextInt();
     System.out.println("--------------------------------------------------------------------");
     if(Tobook==1)
     { sc.nextLine();
    	 bookTicket();
    	 
     }
     else
     {
    	 sc.nextLine();
    	 return;
     }
}













//long way but easy to understand ticket booking for upper lower and middle

/*	if(User_pref.equalsIgnoreCase("LOWER"))
{
	if(lower>0) 
	{
		
	passenger store =new passenger(User_name,User_age,User_gender,User_pref);
	bookedPassenger.add(store) ;
	lower--;
	booked_ticket++;
	confirmed_ticket++;
	System.out.println("lower berth ticket booked Sucessfully");
	return;
	}
	else 
	{
		System.out.println("sorry lower birth is not avaliable");
		System.out.println("avaliable berth are");
		System.out.println("upper berth avaliable ticket:"+upper);
		System.out.println("middle berth avaliable ticket:"+middle);
	}
}
else if(User_pref.equalsIgnoreCase("UPPER"))
{
	if(upper>0) {
	passenger store =new passenger(User_name,User_age,User_gender,User_pref);
	bookedPassenger.add(store) ;
	System.out.println("upper berthticket booked Sucessfully");
	upper--;
	booked_ticket++;
	confirmed_ticket++;
	return;
	}
	else 
	{
		System.out.println("sorry upper birth is not avaliable");
		System.out.println("avaliable berth are");
		System.out.println("lower berth ticket:"+lower);
		System.out.println("middle berth ticket:"+middle);
	}
}
else if(User_pref.equalsIgnoreCase("MIDDLE"))
{
	if(middle>0) {
	passenger store =new passenger(User_name,User_age,User_gender,User_pref);
	bookedPassenger.add(store) ;
	System.out.println("Middle berth ticket booked Sucessfully");
	middle--;
	booked_ticket++;
	confirmed_ticket++;
	return;
	}
	else 
	{
		System.out.println("sorry middle birth is not avaliable");	System.out.println("avaliable berth are");
		System.out.println("upper berth ticket:"+upper);
		System.out.println("lower berth ticket:"+lower);
	}
}
else
{
  System.out.println("invalid berth selection");
}
*/















}
