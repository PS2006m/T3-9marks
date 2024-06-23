/*
 * Problem Definition:
The goal of this project is to develop a simple command-line interface for a basic banking system. The system allows users to perform various banking operations, such as depositing funds, withdrawing funds, and viewing their transaction history (passbook). The program maintains account information using a `Hashtable` and creates individual passbook files for each account in the system.

The system is designed to support the following functionalities:

1. Account Creation and Initialization:
   - The system starts with three pre-defined bank accounts, each identified by a unique account ID.
   - Each account is initialized with an opening balance of 20,000.00 currency units.

2. Banking Operations:
   - Deposit: Users can deposit funds into their account. They provide the account ID and the amount they want to deposit. The system updates the account balance and records the transaction details in the respective account's passbook.
   - Withdraw: Users can withdraw funds from their account. They provide the account ID and the amount they want to withdraw. The system checks if the account has sufficient balance and allows the withdrawal if possible. It updates the account balance and records the transaction details in the respective account's passbook.
   - Print Passbook: Users can view their transaction history (passbook). The system reads the passbook file associated with the given account ID and displays all recorded transactions.

3. Passbook Management:
   - The system creates individual passbook files for each account during program initialization.
   - The passbook file contains the account ID, opening balance, transaction details (deposit or withdrawal), and closing balance.
   - Each passbook file is a simple text file with a well-formatted layout.

4. User Interaction:
   - The system presents a user-friendly command-line interface, continuously asking the user to enter their account ID.
   - If the entered account ID exists in the system, the user is presented with a set of banking options: deposit, withdraw, view passbook, or exit.
   - The user can choose any of the options until they decide to exit the program.

5. File I/O:
   - The system uses file I/O to create, read, and update passbook files for each account.
   - For each transaction (deposit or withdrawal), the system writes the transaction details to the respective account's passbook file.

 */
import java.util.*;
import java.io.*;
class MainPass
{
    public static void main(String[] args) throws IOException
    {
        Random r=new Random();
        Passbook[] ps=new Passbook[3];
        for(int i=0;i<ps.length;i++)
        {
            ps[i]=new Passbook(r.nextInt(100000,999999),20000);
        }
        System.out.println("Pre defined account IDs are");
        for(int i=0;i<ps.length;i++)
        {
            System.out.println(ps[i].accno);
        }
        boolean flag=true;
        Scanner sc=new Scanner(System.in);
        while(flag)
        {
            if(flag==false)
            {
                break;
            }
            System.out.println("Enter your account ID");
            int n=sc.nextInt();
            System.out.println("1.Deposit Amount");
            System.out.println("2.Withdraw Amount");
            System.out.println("3.See PassBook");
            System.out.println("4.Exit");
            System.out.println("Select one of the choices");
            int choice=sc.nextInt();
            switch(choice)
            {
                case 1:System.out.println("Enter amount you wanna enter");
                       double amount1=sc.nextDouble();
                       boolean exists1=true;
                       for(Passbook p:ps)
                       {
                           if(p.accno==n)
                           {
                                p.depositMoney(n,amount1);
                                exists1=false;
                                break;
                           }
                       }
                       if(exists1)
                       {
                        System.out.println("Account does not exist");
                       }
                       break;
                case 2:System.out.println("Enter the amount you wanna withdraw");
                       double amount2=sc.nextDouble();
                       boolean exists2=true;
                       for(Passbook p:ps)
                       {
                           if(p.accno==n)
                           {
                                p.withdrawMoney(n,amount2);
                                exists2=false;
                                break;
                           }
                       }
                       if(exists2)
                       {
                        System.out.println("Account does not exist");
                       }
                       break;
                case 3:boolean exists3=true;
                    for(int i=0;i<ps.length;i++)
                    {
                      if(ps[i].accno==n)
                      {
                         ps[i].printPassbook(n);
                         exists3=false;
                         break;
                     }
                   }
                     if(exists3)
                     {
                          System.out.println("Account does not exist");
                     }
                    break;
                case 4:System.out.println("Thank you for choosing us ");
                        flag=false;
                        break;
                default:System.out.println("Enter between 1 and 4");
                        choice=sc.nextInt();
                        break;
            }
        }
    }
}
class Passbook
{
    int accno;double balance;
    public Passbook(int accountno,double balance) throws IOException
    {
        this.accno=accountno;
        String an;
        an=Integer.toString(accountno);
        this.balance=balance;
        File file=new File(an+".txt");
        FileWriter fw=new FileWriter(file,true);
        fw.write("Your account opened with account number "+an+" and initial balance of "+balance);
    }
    void depositMoney(int accno,double amount) throws IOException
    {
        this.balance=this.balance+amount;
        String an;
        an=Integer.toString(accno);
        FileWriter fw=new FileWriter(an+".txt",true);
        String s="You deposited "+amount+" Rs in your account number of "+accno;
        fw.write(s);
        fw.write("\n");
        fw.close();
        System.out.println("Amount deposited succesfully");
    }
    void withdrawMoney(int accno,double amount) throws IOException
    {
        if(amount>this.balance)
        {
            System.out.println("Insufficient funds in your account");
            String an;
            an=Integer.toString(accno);
            String s="You tried to withdraw "+amount+" but account had insufficient funds in account "+accno;
            FileWriter fw=new FileWriter(an+".txt",true);
            fw.write(s);
            fw.write("\n");
            fw.close();
        }
        else
        {
            this.balance=this.balance-amount;
            System.out.println("Amount withdrawn successfully");
            String an;
            an=Integer.toString(accno);
            String s="You successfully withdrew "+amount+" Rs from your bank account "+accno;
            FileWriter fw=new FileWriter(an+".txt",true);
            fw.write(s);
            fw.write("\n");
            fw.close();
        }
    }
    void printPassbook(int accno) throws IOException
    {
          String an;
          an=Integer.toString(accno);
          FileReader fr=new FileReader(an+".txt");
          int i=fr.read();
          while(i!=-1)
          {
              System.out.print((char)i);
              i=fr.read();
          }
          fr.close();
    }
}