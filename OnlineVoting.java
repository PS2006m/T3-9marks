/*
 * As a Java intern, you have been assigned the task of designing an online voting system using Java 
 * to elect "Best
Captain (Viewers Choice)- IPL 2023". The system is intended to manage the voting process for an election
involving five Indian captains of IPL 2023 . Each captain is associated with a name, age, and team affiliation.
 The
voting process will involve 50 voters, each randomly selecting one of the five captains.
The goal is to implement an efficient and user-friendly system that can handle the following tasks:
1. Storing Captain Information:
 - The system should use a HashMap data structure to store captain information.
 - Each captains details, including their name, age, and team affiliation, will be represented as a `Captain` object.
 - The HashMap will use the captains name as the key and the corresponding `Captain` object as the value.
 - The five captains are Rohit(32 years old, Team MI), Dhoni (39 years old, Team CSK), Virat (30 years old,
Team RCB), Hardik (31 years old, Team GT), and Rishabh (29 years old, Team DC).

2. Displaying Captain Details:
 - Before the voting process begins, the system should display the details of all the captains to the user.
 - The captain details should be printed on the console.

3. Simulating the Voting Process:
 - The voting process will involve 50 voters, each voter will be named Voter-1, Voter-2, and so on, and will
randomly select one of the five captains.
 - The random selection will be done using the Math.random() method.

4. Recording the Voting Sequence:
 - The system should record the voting sequence, indicating the voter name and the captain they voted for.
 - The voting sequence should be stored in a ArrayDeque.

5. Writing Initial Captain Details to File:
 - The system should create a text file named "Voting Result.txt" to store the voting details.
 - Before the voting process begins, the system should write the initial captain details (stored in the HashMap) to
the file.
 - The captain details should be written in a formatted manner for better readability.

6. Writing Voting Sequence to File:
 - After the voting process is complete, the system should update the "Voting Result.txt" file.
 - The voting sequence, including the name of the voter and the captain they voted for, should be written to the
file only.

7. Counting Votes:
 - The system should count the votes received by each captain.
 - The vote count should be stored in a HashMap, where the captain's name is the key, and the vote count is the
value.

8. Sorting and Displaying Voting Results:
 - The system should sort the captains based on the vote count in descending order.
 - The sorted results should be displayed on the console, showing the number of votes and the percentage of
votes gained for each captain.
 - The system should also determine the winner, i.e., the captain with the highest number of votes.
 - The winner's name, team affiliation, total votes, and percentage of votes received should be displayed on the
console.

9. Writing Final Voting Results to File:
 - The system should update the "Voting Result.txt" file with the final voting results.
 - The final voting results, including the number of votes and percentage of votes for each captain, and then the
winner's details, should be written to the file.

10. Error Handling:
 - The system should handle any potential errors during file operations.
 */
import java.util.*;
import java.io.*;
class MainOV 
{
    static int rocount=0,dcount=0,vcount=0,hcount=0,ricount=0;
    public static void main(String[] args) throws IOException
    {
        Scanner sc=new Scanner(System.in);
        File file=new File("Voting Results.txt");
        FileWriter fw=new FileWriter(file);
        BufferedWriter bw=new BufferedWriter(fw);
        HashMap<String,Captain> h1=new HashMap<String,Captain>();
        Captain c1=new Captain("Rohit",32,"MI");
        Captain c2=new Captain("Dhoni",39,"CSK");
        Captain c3=new Captain("Virat",30,"RCB");
        Captain c4=new Captain("Hardik",31,"GT");
        Captain c5=new Captain("Rishabh",29,"DC");
        h1.put("Rohit",c1);
        h1.put("Dhoni",c2);
        h1.put("Virat",c3);
        h1.put("Hardik",c4);
        h1.put("Rishabh",c5);
        VotingProcess vp=new VotingProcess();
        for (Captain c : h1.values()) {
            bw.write(vp.details(c));
            bw.newLine();
        }
       for(Captain c:h1.values())
       {
        System.out.println(vp.details(c));
       }
        ArrayDeque ad=new ArrayDeque<>();
        for(int i=1;i<=5;i++)
        {
            System.out.println("Enter your name Voter number "+i);
            String vname=sc.next();
            double a;String s;
            a=Math.random();
            if(a<=0.02)
            {
                rocount++;
                s="Voter "+i+" name is "+vname+" and they voted for Rohit";
            }
            else if(a<=0.04)
            {
                dcount++;
                s="Voter "+i+" name is "+vname+" and they voted for Dhoni";
            }
            else if(a<=0.06)
            {
                vcount++;
                s="Voter "+i+" name is "+vname+" and they voted for Virat";
            }
            else if(a<=0.08)
            {
                hcount++;
                s="Voter "+i+" name is "+vname+" and they voted for Hardik";
            }
            else
            {
                ricount++;
                s="Voter "+i+" name is "+vname+" and they voted for Rishabh";
            }
            ad.add(s);
            bw.write(s);
            bw.newLine();
        }
        HashMap<String,Integer> h2=new HashMap<String,Integer>();
        h2.put("Rohit",rocount);
        h2.put("Dhoni",dcount);
        h2.put("Virat",vcount);
        h2.put("Hardik",hcount);
        h2.put("Rishabh",ricount);
        c1.setVoteCount(rocount);
        c2.setVoteCount(dcount);
        c3.setVoteCount(vcount);
        c4.setVoteCount(hcount);
        c5.setVoteCount(ricount);
        ArrayList<Captain> al=new ArrayList(h1.values());
        Collections.sort(al,Comparator.comparing(Captain::VoteCount).reversed());
        for(Captain c:al)
        {
            System.out.println(vp.details(c));
        }
    }
}
class Captain
{
    String name,teamName;int age,votecount=0;
    public Captain(String name,int age,String teamName)
    {
        this.name=name;
        this.age=age;
        this.teamName=teamName;
    }
    public void setVoteCount(int votes)
    {
        this.votecount=votes;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getTeamName() {
        return teamName;
    }
    public int VoteCount()
    {
        return votecount;
    }
}
class VotingProcess
{
    String details(Captain c)
    {
        return "Team is "+c.getTeamName()+" Captain name is "+c.getName()+" Captains age is "+c.getAge();
    }
}