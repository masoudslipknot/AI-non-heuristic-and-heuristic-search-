
package ai1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


class State {
    int n,m;
    int deapth;                                      // it is State class 
    int cost;
    int fval;
    char boeard[][]=new char[n][m];
    State parent;
    State child;
    public State(int n,int m){
        this.n=n;
        this.m=m;
        boeard=new char[n][m];
    }

    public void setBoeard(char[][] boeard) {
        this.boeard = boeard;
    }

    public void setFval(int fval) {
        this.fval = fval;
    }
  
    public void setParent(State parent) {
        this.parent = parent;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDeapth(int deapth) {
        this.deapth = deapth;
    }

    public int getDeapth() {
        return deapth;
    }

    public int getCost() {
        return cost;
    }

    public State getParent() {
        return parent;
    }

    public void setChild(State child) {
        this.child = child;
    }

    @Override
    public int hashCode() {
        String answer="";
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                answer=answer+boeard[i][j];
            }
        }
        return answer.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (this.n != other.n) {
            return false;
        }
        if (this.m != other.m) {
            return false;
        }
        if (!Arrays.deepEquals(this.boeard, other.boeard)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}



public class AI1 {
    
    public static int compare(State first,State secend){                      // this method is used for comparing two states
        int n=first.n;
        int m=first.m;
        int result=1;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(first.boeard[i][j]!=secend.boeard[i][j]){
                    result=0;
                    break;
                }
            }
        }
        return result;
    }
   public static boolean serachset(Set <State>hashset,State input){           //this method is used for searching in set in order to find if an revisited state exists
             boolean answer=false;
       for(State find:hashset){
           
           int result=compare(find, input);
           if(result==1){
               answer=true;
               break;
           }else{
               continue;
           }
           
       }
       return answer;
   }
   public static boolean serachfringe(Queue<State> Fringe,State input){       //it is used for searching in fringe lists.
       boolean answer=false;
       for(State find:Fringe){
           
           int result=compare(input, find);
           if(result==1){
               answer=true;
               break;
           }else{
               
               continue;
           }
       }
       return answer;
   }
    public static boolean serachprirityfringe(PriorityQueue<State> Fringe,State input){    // again it is used for searching in fringe list that is used for ucs
       boolean answer=false;
       for(State find:Fringe){
           
           int result=compare(input, find);
           if(result==1){
               answer=true;
               break;
           }else{
               
               continue;
           }
       }
       return answer;
   }
       
   public static boolean searchstack(Stack<State> Fringe,State input){
         boolean answer=false;
       for(State find:Fringe){
           
           int result=compare(input, find);
           if(result==1){
               answer=true;
               break;
           }else{
               
               continue;
           }
       }
       return answer;
       
   }
    
    public static int herustic(State first,State Goal){
        int count=0;
        for(int i=0;i<first.n;i++){
            for(int j=0;j<first.m;j++){
                if(first.boeard[i][j]!=Goal.boeard[i][j]){
                    count++;
                }
            }
        }
        
        return count;
    }
    
    
public static  List sucsasor(State current){                //this method is used as roles to find next states that are possible
    ArrayList<State> nextstates=new ArrayList<>();
    int n=current.n;
    int m=current.m;
    int curborad[][]=new int [n][m];
    
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            if(current.boeard[i][j]=='#'){
                curborad[i][j]=-1;
                if(i-1>=0)
                  curborad[i-1][j]  =curborad[i-1][j]+i+1;
                if(i-1>=0 && j-1 >=0)
                   curborad[i-1][j-1]= curborad[i-1][j-1]+i+1;
                if(i-1>=0 && j+1<m)
                curborad[i-1][j+1]  =  curborad[i-1][j+1]+i+1;
                if(i+1<n && j-1>=0)
                 curborad[i+1][j-1]  = curborad[i+1][j-1]+i+1;
                if(i+1<n && j+1<m)
                   curborad[i+1][j+1]= curborad[i+1][j+1]+i+1;
                if(j-1>=0)
                curborad[i][j-1]  =  curborad[i][j-1]+i+1;
                if(i+1<n)
               curborad[i+1][j]   =  curborad[i+1][j]+i+1;
                if(j+1<m)
                 curborad[i][j+1] =  curborad[i][j+1]+i+1;
                }
                    
            }
        }
    for(int i=0;i<n;i++){                         // here we have different situations
        for(int j=0;j<m;j++){
            if(curborad[i][j]==-1){
                                                   char [][]nextboeard= new char[n][m];
                        for(int v=0;v<n;v++){
                    for(int x=0;x<m;x++){
                       nextboeard[v][x]  =  current.boeard[v][x];  
                    }
                }
                nextboeard[i][j]='.';
                State nextstate=new State(n, m);
                nextstate.setBoeard(nextboeard);
                if(i<j){
                    nextstate.setCost(j);
                }else if (i>=j){
                    nextstate.setCost(i);
                }
                nextstate.setDeapth(current.deapth+1);
                
                nextstates.add(nextstate);
                if(i-2>=0)
                if(curborad[i-2][j]==i+1||curborad[i-2][j]==0){
                                                     char [][]nextboeard2= new char[n][m];
                        for(int v=0;v<n;v++){
                    for(int x=0;x<m;x++){
                       nextboeard2[v][x]  =  current.boeard[v][x];  
                    }
                }
                   
                   nextboeard2[i][j]='.';                                            //different states 
                    nextboeard2[i-2][j]='#';
                    State nextstate2=new State(n,m);
                    nextstate2.setBoeard(nextboeard2);
                     nextstate2.setDeapth(current.deapth+1);
                    nextstates.add(nextstate2);
                   
                    nextstate2.setCost(2);
                }
                if(i-1>=0)
                    if(curborad[i-1][j]==i+1||curborad[i-1][j]==0){
                                                    char [][]nextboeard3= new char[n][m];
                        for(int v=0;v<n;v++){
                    for(int x=0;x<m;x++){
                       nextboeard3[v][x]  =  current.boeard[v][x];  
                    }
                }
                          nextboeard3[i][j]='.';
                    nextboeard3[i-1][j]='#';
                    State nextstate3=new State(n,m);
                    nextstate3.setBoeard(nextboeard3);
                     nextstate3.setDeapth(current.deapth+1);
                    nextstates.add(nextstate3);
                     
                    nextstate3.setCost(1);
                    }
                if(j+1<m)
                    if(curborad[i][j+1]==i+1||curborad[i][j+1]==0){
                                              char [][]nextboeard4= new char[n][m];
                        for(int v=0;v<n;v++){
                    for(int x=0;x<m;x++){
                       nextboeard4[v][x]  =  current.boeard[v][x];  
                    }
                }
                          nextboeard4[i][j]='.';
                    nextboeard4[i][j+1]='#';
                    State nextstate4=new State(n,m);
                    nextstate4.setBoeard(nextboeard4);
                    nextstate4.setDeapth(current.deapth+1);
                    nextstates.add(nextstate4);
                   
                    nextstate4.setCost(1);
                    }
                if(j+2<m)
                      if(curborad[i][j+1]==i+1||curborad[i][j+1]==0){
                                        char [][]nextboeard5= new char[n][m];
                        for(int v=0;v<n;v++){
                    for(int x=0;x<m;x++){
                       nextboeard5[v][x]  =  current.boeard[v][x];  
                    }
                }
                          nextboeard5[i][j]='.';
                    nextboeard5[i][j+2]='#';
                    State nextstate5=new State(n,m);
                    nextstate5.setBoeard(nextboeard5);
                     nextstate5.setDeapth(current.deapth+1);
                    nextstates.add(nextstate5);
                    
                    nextstate5.setCost(2);
                      }
                if(i+1<n)
                       if(curborad[i+1][j]==i+1||curborad[i+1][j]==0){
                                   char [][]nextboeard6= new char[n][m];
                        for(int v=0;v<n;v++){
                    for(int x=0;x<m;x++){
                       nextboeard6[v][x]  =  current.boeard[v][x];  
                    }
                }
                          nextboeard6[i][j]='.';
                    nextboeard6[i+1][j]='#';
                    State nextstate6=new State(n,m);
                    nextstate6.setBoeard(nextboeard6);
                      nextstate6.setDeapth(current.deapth+1);
                    nextstates.add(nextstate6);
                     
                    nextstate6.setCost(1);
                      }
                if(i+2<n){
                 
                      if(curborad[i+2][j]==i+1||curborad[i+2][j]==0){
                               char [][]nextboeard7= new char[n][m];
                        for(int v=0;v<n;v++){
                    for(int x=0;x<m;x++){
                       nextboeard7[v][x]  =  current.boeard[v][x];  
                    }
                }
                          nextboeard7[i][j]='.';
                    nextboeard7[i+2][j]='#';
                    State nextstate7=new State(n,m);
                    nextstate7.setBoeard(nextboeard7);
                     nextstate7.setDeapth(current.deapth+1);
                    nextstates.add(nextstate7);
                      
                    nextstate7.setCost(2);
                      }
                }
                if(j-1>=0)
                      if(curborad[i][j-1]==i+1||curborad[i][j-1]==0){
                           char [][]nextboeard8= new char[n][m];
                        for(int v=0;v<n;v++){
                    for(int x=0;x<m;x++){
                       nextboeard8[v][x]  =  current.boeard[v][x];  
                    }
                }
                          nextboeard8[i][j]='.';
                    nextboeard8[i][j-1]='#';
                    State nextstate8=new State(n,m);
                    nextstate8.setBoeard(nextboeard8);
                      nextstate8.setDeapth(current.deapth+1);
                    nextstates.add(nextstate8);
                     
                    nextstate8.setCost(1);
                      }
                if(j-2>=0)
                     if(curborad[i][j-2]==i+1||curborad[i][j-2]==0){
                        char [][]nextboeard9= new char[n][m];
                        for(int v=0;v<n;v++){
                    for(int x=0;x<m;x++){
                       nextboeard9[v][x]  =  current.boeard[v][x];  
                    }
                }
                          nextboeard9[i][j]='.';
                    nextboeard9[i][j-2]='#';
                    State nextstate9=new State(n,m);
                    nextstate9.setBoeard(nextboeard9);
                     nextstate9.setDeapth(current.deapth+1);
                    nextstates.add(nextstate9);
                        
                    nextstate9.setCost(2);
                      }
                    
            }else if(curborad[i][j]==0){
                char [][]nextboeard10= new char[n][m];
                for(int v=0;v<n;v++){
                    for(int x=0;x<m;x++){
                       nextboeard10[v][x]  =  current.boeard[v][x];  
                    }
                }
                   
                nextboeard10[i][j]='#';
                State nextstate10=new State(n, m);
                nextstate10.setBoeard(nextboeard10);
                 nextstate10.setDeapth(current.deapth+1);
                  int cost=0;
                 if(i>j) cost=i;
                         else if(i<=j) cost=j;
                  
                    if(cost <0)  cost=-cost;
                    nextstate10.setCost(cost);
                
                nextstates.add(nextstate10);
            }
        }
    }
    
     
    
    
    
    return nextstates;
}


   public static int  printtheanswer(State print,String name){                      //this method is used for printing the path from start to Goal
        Stack <String>actions=new Stack();
        int cout=0;
       while(print.parent!=null){
           
            int counter=0;
    int decide=0;
    int secendposx=0;
    int secendposy=0;
    int tool=0;
            int arz=0;
    for(int i=0;i<print.parent.n;i++){
        for(int j=0;j<print.parent.m;j++){
            
            if(print.parent.boeard[i][j]!=print.boeard[i][j]){
                if(counter==0){
                    ///char firstDIF=(char) (first.boeard[i][j]-secend.boeard[i][j]);
                    if(print.parent.boeard[i][j]=='#'){
                        decide=1;   // remove
                         tool=i;  // position
                     arz=j;
                    }else if (print.boeard[i][j]=='#'){
                        decide=0;   // add
                          tool=i;  // position
                     arz=j;
                  
                    }
                         
                    counter++;
                }else{
                   
                        
                    secendposx=i;
                    secendposy=j;
                  
                       
                    counter++;
                    
                }
                
            }
        }
        
    }
    if(counter==1&&decide==1){
      //  System.out.println("Remove"+"("+tool+","+arz+")");
        actions.add("Remove"+"("+tool+","+arz+")");
    }else if (counter==1&&decide==0){
      //   System.out.println("Add"+"("+tool+","+arz+")");
         actions.add("Add"+"("+tool+","+arz+")");
    }else if(counter==2&&decide==1){
        //System.out.println("move"+"("+tool+","+arz+")" + " "+ "to"+ " "+ "("+secendposx+","+secendposy+")");
          actions.add("move"+"("+tool+","+arz+")" + " "+ "to"+ " "+ "("+secendposx+","+secendposy+")");
    }else if(counter==2&&decide==0){
        actions.add("move"+"("+secendposx+","+secendposy+")" + " "+ "to"+ " "+ "("+tool+","+arz+")");
    }
        counter=0;
        decide=0;
    print=print.parent;
       }
       if(!name.equals("BDS"))
       System.out.println("Actions"+actions.size());
       cout=actions.size();
       while(actions.size()!=0){
           System.out.println(actions.pop());
       }
       return cout;
   }
   public static int  printtheanswer2(State print){                     //this method is used for printing the path from start to Goal only for Bds
                       int result=0;                                                   // for its secend tree
        Queue <String>actions=new LinkedList<>();
       while(print.parent!=null){
           
            int counter=0;
    int decide=0;
    int secendposx=0;
    int secendposy=0;
    int tool=0;
            int arz=0;
    for(int i=0;i<print.parent.n;i++){
        for(int j=0;j<print.parent.m;j++){
            
            if(print.parent.boeard[i][j]!=print.boeard[i][j]){
                if(counter==0){
                    ///char firstDIF=(char) (first.boeard[i][j]-secend.boeard[i][j]);
                    if(print.boeard[i][j]=='#'){
                        decide=1;   
                         tool=i;  // position
                     arz=j;
                    }else if (print.parent.boeard[i][j]=='#'){
                        decide=0;   
                          tool=i;  // position
                     arz=j;
                  
                    }
                         
                    counter++;
                }else{
                   
                        
                    secendposx=i;
                    secendposy=j;
                  
                       
                    counter++;
                    
                }
                
            }
        }
        
    }
    if(counter==1&&decide==1){
      //  System.out.println("Remove"+"("+tool+","+arz+")");
        actions.add("Remove"+"("+tool+","+arz+")");
    }else if (counter==1&&decide==0){
      //   System.out.println("Add"+"("+tool+","+arz+")");
         actions.add("Add"+"("+tool+","+arz+")");
    }else if(counter==2&&decide==1){
        //System.out.println("move"+"("+tool+","+arz+")" + " "+ "to"+ " "+ "("+secendposx+","+secendposy+")");
          actions.add("move"+"("+tool+","+arz+")" + " "+ "to"+ " "+ "("+secendposx+","+secendposy+")");
    }else if(counter==2&&decide==0){
        actions.add("move"+"("+secendposx+","+secendposy+")" + " "+ "to"+ " "+ "("+tool+","+arz+")");
    }
        counter=0;
        decide=0;
    print=print.parent;
       }
      // System.out.println("Actions"+actions.size());
      result=actions.size();
       while(actions.size()!=0){
           System.out.println(actions.poll());
       }
       return result;
   }
   public static int getTotalcost(State s){                 // it is used in ucs for counting the total cost from start to the node
        int cout=0;
        while(s!=null){
            cout=s.cost+cout;
            s=s.parent;
        }
        return cout;
    }
    public static void main(String[] args)   {
        
        System.out.println("please enter which alghorithm do you want?");
        Scanner myscanner=new Scanner(System.in);
       int alghorithm= myscanner.nextInt();
       
       if(alghorithm==1){
           //Bfs
           long mill;
           long end;
        int n=0,m=0;
        Queue<State> Fringe = new LinkedList<State>();
     File file = new File("in.txt");
        mill = System.currentTimeMillis();
        
        
    try {

        Scanner sc = new Scanner(file);
       
        while (sc.hasNextInt()) {
             n = sc.nextInt();
             m=sc.nextInt();
        }
         
        State instationstate=new State(n, m);                         // reading from input
        State Goalstate=new State(n,m);
        int i=0;
        int j=0;
        while (sc.hasNextLine()){
            String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                instationstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
        //sc.nextLine();
        i=0;
        while(sc.hasNextLine()){
            
            j=0;
             String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                Goalstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
       sc.close();
         instationstate.setDeapth(0);
        ArrayList<State> nextstates=new ArrayList<>();
        Fringe.add(instationstate);
        Set <State>visitedlist=new HashSet<State>();
        visitedlist.add(instationstate);
        int generatednodes=1;
        int ramnodes=1;
        int firsttime=1;
        while( Fringe.size()!=0){
            
            int result=compare(Goalstate, Fringe.peek());
           // System.out.println("hi");
            State lastone=Fringe.peek();
            if(result==0){
                State father=Fringe.peek();
                
            nextstates=(ArrayList<State>) sucsasor(Fringe.poll());
            visitedlist.add(father);
            generatednodes=generatednodes+nextstates.size();
               ramnodes=Math.max(Fringe.size()+visitedlist.size(),ramnodes);
            
        for(int v=0;v<nextstates.size();v++){
            
            State now=nextstates.get(v);
            if(!visitedlist.contains(now)){
                if(firsttime!=1){
              if(!Fringe.contains(now)){ 
            now.setParent(father);
            Fringe.add(now);
              }
               
                }else if(firsttime==1){
                    now.setParent(father);
            Fringe.add(now);
            firsttime++;
                }
            
            }
        }
            }else{
               // Fringe.poll();
                //printanswer(lastone);
                System.out.println("Alghorithm : "+ "BFS");
               int b=printtheanswer(lastone,"BFS");
                end = System.currentTimeMillis();
                long time=end-mill;
               // time=time / 1000;
               System.out.println("Number of generated nodes "+ generatednodes);
           
               System.out.println("Maximom nodes in Ram"+" "+ramnodes);
                System.out.println("Time "+ ":"+time+"mill");
                break;
                //print the answer
            }
        }
        //System.out.println(nextstates.size());
    } 
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }
 
    }else if(alghorithm==2){ 
        //Dfs
         long mill;
           long end;
        Stack<State> myStack = new Stack();
        int n=0,m=0;
        File file = new File("in.txt");
        mill = System.currentTimeMillis();
        
        try {

        Scanner sc = new Scanner(file);
       
        while (sc.hasNextInt()) {
             n = sc.nextInt();
             m=sc.nextInt();
        }
         
        State instationstate=new State(n, m);
        State Goalstate=new State(n,m);
        int i=0;
        int j=0;
        while (sc.hasNextLine()){
            String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                instationstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
       
        i=0;
        while(sc.hasNextLine()){
            
            j=0;
             String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                Goalstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
       sc.close();
        instationstate.setDeapth(0);
       ArrayList<State> nextstates=new ArrayList<>();
       //Set<State> visitedstates=new HashSet<State>();
       int nodesgenerated=1;
       int ramnodes=1;
       myStack.add(instationstate);
     
           while(myStack.size()!=0){
              int result=compare(Goalstate, myStack.peek());
            State lastone=myStack.peek();
            if(result==0){
                nextstates=(ArrayList<State>) sucsasor(myStack.pop());
                nodesgenerated=nextstates.size()+nodesgenerated;
                int firsttime=1;
                ramnodes=Math.max(myStack.size(),ramnodes);
               // visitedstates.add(lastone);
                for(int v=0;v<nextstates.size();v++){
                    
                    State current=nextstates.get(v);
                    State now=new State(lastone.n, lastone.m);
                    now.setBoeard(lastone.boeard);
                    now.setParent(lastone.parent);
                     int res=0;
                   
                          
                        
                          
                        
                       // if(!searchstack(myStack, current)){
                            while(now!=null){
                                res=compare(now, current);
                                if(res==0){
                                       now=now.parent;
                            
                                }else{
                                    break;
                                }
                            }
                            if(now==null){
                             
                                    myStack.add(current);
                            current.setParent(lastone);
                                
                            }
                           
                     //  }
                      
                        
                       
                    
                }
                 firsttime++;
               // maxlevel++;
                
            }else{
                 System.out.println("Alghorithm : "+ "DFS");
                int b=    printtheanswer(lastone,"DFS");
                    end = System.currentTimeMillis();
                long time=end-mill;
                 System.out.println("Number of generated nodes "+ nodesgenerated);
                  System.out.println("Maximom nodes in Ram"+" "+ramnodes);
                System.out.println("Time "+ ":"+time+"mill");
               
                    break;
            }
            
           }
           
          
       }
    
       
       
        

    catch (FileNotFoundException e) {
        e.printStackTrace();
    }
        
        
    }else if(alghorithm==3){                          //ucs 
        
           long mill;
           long end;
        int n=0,m=0;
         File file = new File("in.txt");
           mill = System.currentTimeMillis();
           PriorityQueue<State> Fringe =new PriorityQueue<>(9999999, (State o1, State o2) -> {
               
               if(o1.cost> o2.cost){
                   return 1;
               }else if(o1.cost < o2.cost){
                  return -1 ;
               }else{
                   return 0;
               }
               //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               
           });
         try {

        Scanner sc = new Scanner(file);
       
        while (sc.hasNextInt()) {
             n = sc.nextInt();
             m=sc.nextInt();
        }
         
        State instationstate=new State(n, m);
         instationstate.setDeapth(0);
        State Goalstate=new State(n,m);
        int i=0;
        int j=0;
        while (sc.hasNextLine()){
            String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                instationstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
        //sc.nextLine();
        i=0;
        while(sc.hasNextLine()){
            
            j=0;
             String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                Goalstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
       sc.close();
       
         
          instationstate.setDeapth(0);
        ArrayList<State> nextstates=new ArrayList<>();
        Fringe.add(instationstate);
        Set <State> visitedlist=new HashSet<State>();
        visitedlist.add(instationstate);
        int generatednodes=1;
        int ramnodes=1;
       
        while( Fringe.size()!=0){
            
            int result=compare(Goalstate, Fringe.peek());
           // System.out.println("hi");
            State lastone=Fringe.peek();
            if(result==0){
                State father=Fringe.peek();
                
            nextstates=(ArrayList<State>) sucsasor(Fringe.poll());
            visitedlist.add(father);
            generatednodes=generatednodes+nextstates.size();
               ramnodes=Math.max(Fringe.size()+visitedlist.size(),ramnodes);
            
        for(int v=0;v<nextstates.size();v++){
            
            State now=nextstates.get(v);
            if(!visitedlist.contains(now)){
                
               if(!Fringe.contains(now)){
            now.setParent(father);
           int totalcost=getTotalcost(now);
           now.setCost(totalcost);
            //System.out.println(now.cost);
            Fringe.add(now);
               
                }
            
            
            }
        }
            }else{
               // Fringe.poll();
                //printanswer(lastone);
                System.out.println(lastone.cost);
                System.out.println("Alghorithm : "+ "UCS");
              int b=  printtheanswer(lastone,"UCS");
                end = System.currentTimeMillis();
                long time=end-mill;
               // time=time / 1000;
               System.out.println("Number of generated nodes "+ generatednodes);
           
               System.out.println("Maximom nodes in Ram"+" "+ramnodes);
                System.out.println("Time "+ ":"+time+"mill");
                break;
                //print the answer
            }
        }
         }
          catch (FileNotFoundException e) {
        e.printStackTrace();
    } 
                                                                    
    }else if (alghorithm==4){       
        // BDs
                long mill;
           long end;
        int n=0,m=0;
        
        int ramnodes=1;
        
               File file = new File("in.txt");
        mill = System.currentTimeMillis();
         
    try {

        Scanner sc = new Scanner(file);
       
        while (sc.hasNextInt()) {
             n = sc.nextInt();
             m=sc.nextInt();
        }
         
        State instationstate=new State(n, m);
        State Goalstate=new State(n,m);
        int i=0;
        int j=0;
        int nodesgenerated=1;
        while (sc.hasNextLine()){
            String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                instationstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
        //sc.nextLine();
        i=0;
        while(sc.hasNextLine()){
            
            j=0;
             String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                Goalstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
       sc.close();
       boolean Found=false;
       ArrayList<State> Firstnextstates=new ArrayList<>();
        State q,q1 = null,q2 = null;
       ArrayList<State> Goalnextstates=new ArrayList<>();
        ArrayList<State> Goal=new ArrayList<>();
         ArrayList<State> First=new ArrayList<>();
       Set <State>visitedlist=new HashSet<State>();
        Set <State>visitedlist2=new HashSet<State>();
      
       Queue<State> instationqueue=new LinkedList<State>();
       Queue<State> Goalqueue=new LinkedList<State>();
       instationqueue.add(instationstate);
       Goalqueue.add(Goalstate);
        State swap=null;
        State well=null;
        while(!Found){
       while(instationqueue.size()!=0 && Goalqueue.size()!=0){
           State Father=instationqueue.peek();
           State childGoal=Goalqueue.peek();
           if(instationqueue.size()!=0)
       Firstnextstates=(ArrayList<State>) sucsasor(instationqueue.poll());
           
           if(Goalqueue.size()!=0)
       Goalnextstates=(ArrayList<State>) sucsasor(Goalqueue.poll());
        visitedlist.add(Father);
        visitedlist2.add(childGoal);
        nodesgenerated=nodesgenerated+Firstnextstates.size()+Goalnextstates.size();
        ramnodes=Math.max(instationqueue.size()+visitedlist.size()+visitedlist2.size()+Goalqueue.size(),ramnodes);
       for(int b=0;b<Firstnextstates.size();b++){
           First.add(Firstnextstates.get(b));
           Firstnextstates.get(b).setParent(Father);
            
       }
       for(int b=0;b<Goalnextstates.size();b++){
           Goal.add(Goalnextstates.get(b));
           Goalnextstates.get(b).setParent(childGoal);
            
       }
     
       
       
       
       
       }
       for(int b=0;b<First.size();b++){
            State first=First.get(b);
            
           for(int y=0;y< Goal.size();y++){
          
          
         
         State  secend =Goal.get(y);
           
          
           int compare=compare(first, secend);
           
           if(compare==1){
               
             swap=new State(secend.n,secend.m);
               swap.setBoeard(secend.boeard);
               swap.setParent(secend.parent);  
                
         
        
             well=new State(first.n,first. m);
             well.setBoeard(first.boeard);
             well.setParent(first.parent);
               Found=true;
               break;
           }else{
               
               if(!visitedlist.contains(first)){
               if(!instationqueue.contains(first)){
              instationqueue.add(first);
               }
               }
          
           if(!visitedlist2.contains(secend)){
               
            if(!Goalqueue.contains(secend)){
               
            
               Goalqueue.add(secend);
            }
           }
           }
           
           }
       }
       if(Found==true) break;
       
       
       }
 System.out.println("Alghorithm : "+ "BDS");
             int b=  printtheanswer(well,"BDS");
              int c=  printtheanswer2(swap);
              int res=b+c;
              System.out.println("Actions"+res);
                end = System.currentTimeMillis();
                long time=end-mill;
               // time=time / 1000;
               System.out.println("Number of generated nodes "+ nodesgenerated);
           
               System.out.println("Maximom nodes in Ram"+" "+ramnodes);
                System.out.println("Time "+ ":"+time+"mill");
                       
    }catch (FileNotFoundException e) {
        e.printStackTrace();
    } 
                                                         
    }else if(alghorithm==5){
             // idc
               long mill;
           long end;
        int n=0,m=0;
        int maxlevel=0;
        int ramnodes=1;
               File file = new File("in.txt");
        mill = System.currentTimeMillis();
         
    try {

        Scanner sc = new Scanner(file);
       
        while (sc.hasNextInt()) {
             n = sc.nextInt();
             m=sc.nextInt();
        }
         
        State instationstate=new State(n, m);
        State Goalstate=new State(n,m);
        int i=0;
        int j=0;
        int nodesgenerated=1;
        while (sc.hasNextLine()){
            String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                instationstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
        //sc.nextLine();
        i=0;
        while(sc.hasNextLine()){
            
            j=0;
             String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                Goalstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
       sc.close();
       ArrayList<State> nextstates=new ArrayList<>();
        Stack<State> myStack = new Stack();
        instationstate.setDeapth(0);
          myStack.add(instationstate);
       maxlevel=0;
        boolean Goalfound=false;
       while( !Goalfound){
         
           while(myStack.size()!=0){
              int result=compare(Goalstate, myStack.peek());
            State lastone=myStack.peek();
            if(result==0){
                 //maxlevel=lastone.getDeapth();
                nextstates=(ArrayList<State>) sucsasor(myStack.pop());
                nodesgenerated=nextstates.size()+nodesgenerated;
                int firsttime=1;
                ramnodes=Math.max(myStack.size(),ramnodes);
               // visitedstates.add(lastone);
                for(int v=0;v<nextstates.size();v++){
                    
                    State current=nextstates.get(v);
                    State now=new State(lastone.n, lastone.m);
                    now.setBoeard(lastone.boeard);
                    now.setParent(lastone.parent);
                     int res=0;
                   
                          
                        
                          
                        
                        for(int h=0;h<=maxlevel;h++){
                            if(current.deapth==h){
                            while(now!=null){
                                res=compare(now, current);
                                if(res==0){
                                       now=now.parent;
                            
                                }else{
                                    break;
                                }
                            }
                                   if(now==null){
                                    myStack.add(current);
                            current.setParent(lastone);
                            }
                            }
                           
                        }
                      
                      
                       
                    
                }
                 if(myStack.size()==0)
                            break;
               // maxlevel++;
                
            }else{
                 System.out.println("Alghorithm : "+ "IDS");
                 int a=   printtheanswer(lastone,"IDS");
                    end = System.currentTimeMillis();
                long time=end-mill;
                 System.out.println("Number of generated nodes "+ nodesgenerated);
                  System.out.println("Maximom nodes in Ram"+" "+ramnodes);
                System.out.println("Time "+ ":"+time+"mill");
                Goalfound=true;
                    break;
            }
           
           }
            if(Goalfound==true){
                break;
            }
        
           maxlevel++;
             myStack.add(instationstate);
             instationstate.setDeapth(0);
       }
       
       
       
    }
         catch (FileNotFoundException e) {
        e.printStackTrace();
    }     
    }else if(alghorithm==6){       // GBS
        
               long mill;
           long end;
        int n=0,m=0;
         File file = new File("in.txt");
           mill = System.currentTimeMillis();
        
         try {

        Scanner sc = new Scanner(file);
       
        while (sc.hasNextInt()) {
             n = sc.nextInt();
             m=sc.nextInt();
        }
         
        State instationstate=new State(n, m);
         instationstate.setDeapth(0);
        State Goalstate=new State(n,m);
        int i=0;
        int j=0;
        while (sc.hasNextLine()){
            String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                instationstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
        //sc.nextLine();
        i=0;
        while(sc.hasNextLine()){
            
            j=0;
             String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                Goalstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
       sc.close();
       
         
          instationstate.setDeapth(0);
        ArrayList<State> nextstates=new ArrayList<>();
           PriorityQueue<State> Fringe =new PriorityQueue<>(9999999, (State o1, State o2) -> {
               
               if(herustic(o1, Goalstate)> herustic(o2, Goalstate)){
                   return 1;
               }else if(herustic(o1, Goalstate) < herustic(o2, Goalstate)){
                  return -1 ;
               }else{
                   return 0;
               }
               //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               
           });
        Fringe.add(instationstate);
        Set <State> visitedlist=new HashSet<State>();
        visitedlist.add(instationstate);
        int generatednodes=1;
        int ramnodes=1;
       
        while( Fringe.size()!=0){
            
            int result=compare(Goalstate, Fringe.peek());
           // System.out.println("hi");
            State lastone=Fringe.peek();
            if(result==0){
                State father=Fringe.peek();
                
            nextstates=(ArrayList<State>) sucsasor(Fringe.poll());
            visitedlist.add(father);
            generatednodes=generatednodes+nextstates.size();
               ramnodes=Math.max(Fringe.size()+visitedlist.size(),ramnodes);
            
        for(int v=0;v<nextstates.size();v++){
            
            State now=nextstates.get(v);
            if(!visitedlist.contains(now)){
                
               
            now.setParent(father);
        //   int totalcost=getTotalcost(now);
          // now.setCost(totalcost);
            //System.out.println(now.cost);
            Fringe.add(now);
               
                
            
            
            }
        }
            }else{
               // Fringe.poll();
                //printanswer(lastone);
                System.out.println("Alghorithm : "+ "GBS");
              int b=  printtheanswer(lastone,"GBS");
                end = System.currentTimeMillis();
                long time=end-mill;
               // time=time / 1000;
               System.out.println("Number of generated nodes "+ generatednodes);
           
               System.out.println("Maximom nodes in Ram"+" "+ramnodes);
                System.out.println("Time "+ ":"+time+"mill");
                break;
                //print the answer
            }
        }
         }
          catch (FileNotFoundException e) {
        e.printStackTrace();
    } 
        
        
        
        
        
        
        
    }else if(alghorithm==7){             // A*
        
                long mill;
           long end;
        int n=0,m=0;
         File file = new File("in.txt");
           mill = System.currentTimeMillis();
        
         try {

        Scanner sc = new Scanner(file);
       
        while (sc.hasNextInt()) {
             n = sc.nextInt();
             m=sc.nextInt();
        }
         
        State instationstate=new State(n, m);
         instationstate.setDeapth(0);
        State Goalstate=new State(n,m);
        int i=0;
        int j=0;
        while (sc.hasNextLine()){
            String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                instationstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
        //sc.nextLine();
        i=0;
        while(sc.hasNextLine()){
            
            j=0;
             String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                Goalstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
       sc.close();
       
         
          instationstate.setDeapth(0);
        ArrayList<State> nextstates=new ArrayList<>();
           PriorityQueue<State> Fringe =new PriorityQueue<>(9999999, (State o1, State o2) -> {
               
               if(herustic(o1,Goalstate)+(getTotalcost(o1))> herustic(o2,Goalstate)+(getTotalcost(o2))){
                   return 1;
               }else if(herustic(o1,Goalstate)+(getTotalcost(o1)) < herustic(o2,Goalstate)+(getTotalcost(o2))){
                  return -1 ;
               }else{
                   return 0;
               }
               //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               
           });
        Fringe.add(instationstate);
        Set <State> visitedlist=new HashSet<State>();
        visitedlist.add(instationstate);
        int generatednodes=1;
        int ramnodes=1;
       
        while( Fringe.size()!=0){
            
            int result=compare(Goalstate, Fringe.peek());
           // System.out.println("hi");
            State lastone=Fringe.peek();
            if(result==0){
                State father=Fringe.peek();
                   
            nextstates=(ArrayList<State>) sucsasor(Fringe.poll());
            ramnodes=Math.max(Fringe.size()+visitedlist.size(),ramnodes);
            visitedlist.add(father);
            generatednodes=generatednodes+nextstates.size();
              
            
        for(int v=0;v<nextstates.size();v++){
            
            State now=nextstates.get(v);
            if(!visitedlist.contains(now)){
                
               if(!serachprirityfringe(Fringe, now)){
            now.setParent(father);
         //  int totalcost=getTotalcost(now);
         // now.setCost(totalcost);
            //System.out.println(now.cost);
          
            Fringe.add(now);
               
                }
            
            
            }
        }
            }else{
               // Fringe.poll();
                //printanswer(lastone);
                System.out.println("Alghorithm : "+ "A*");
              int b=  printtheanswer(lastone,"A*");
                end = System.currentTimeMillis();
                long time=end-mill;
               // time=time / 1000;
               System.out.println("Number of generated nodes "+ generatednodes);
           
               System.out.println("Maximom nodes in Ram"+" "+ramnodes);
                System.out.println("Time "+ ":"+time+"mill");
                break;
                //print the answer
            }
        }
         }
          catch (FileNotFoundException e) {
        e.printStackTrace();
    } 
        
        
        
        
    }else if(alghorithm==8){  //iDA*
        
                    long mill;
                    int min=99999999;
                    
           long end;
        int n=0,m=0;
        int cutoff=0;
        int nodesgenerated=1;
         File file = new File("in.txt");
           mill = System.currentTimeMillis();
        
         try {

        Scanner sc = new Scanner(file);
       
        while (sc.hasNextInt()) {
             n = sc.nextInt();
             m=sc.nextInt();
        }
         
        State instationstate=new State(n, m);
         instationstate.setDeapth(0);
        State Goalstate=new State(n,m);
        int i=0;
        int j=0;
        while (sc.hasNextLine()){
            String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                instationstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
        //sc.nextLine();
        i=0;
        while(sc.hasNextLine()){
            
            j=0;
             String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                Goalstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
       sc.close();
           instationstate.setDeapth(0);
        ArrayList<State> nextstates=new ArrayList<>();
        Stack<State> myStack = new Stack();
        instationstate.setDeapth(0);
          myStack.add(instationstate);
        int ramnodes=1;
        boolean GoalFound=false;
        cutoff=getTotalcost(instationstate)+herustic(instationstate, Goalstate);
        while(!GoalFound){
        while(myStack.size()!=0){
              int result=compare(Goalstate, myStack.peek());
            State lastone=myStack.peek();
            if(result==0){
                 //maxlevel=lastone.getDeapth();
                nextstates=(ArrayList<State>) sucsasor(myStack.pop());
                 nodesgenerated=nextstates.size()+nodesgenerated+myStack.size();
                 if(getTotalcost(lastone)+herustic(lastone, Goalstate)<=cutoff){
               
                ramnodes=Math.max(myStack.size(),ramnodes);
               // visitedstates.add(lastone);
                for(int v=0;v<nextstates.size();v++){
                    
                    State current=nextstates.get(v);
                    State now=new State(lastone.n, lastone.m);
                    now.setBoeard(lastone.boeard);
                    now.setParent(lastone.parent);
                     int res=0;
                   
                          
                        
                          
                        
                        
                           
                            while(now!=null){
                                res=compare(now, current);
                                if(res==0){
                                       now=now.parent;
                            
                                }else{
                                    break;
                                }
                            }
                                   if(now==null){
                                    myStack.add(current);
                            current.setParent(lastone);
                            }
                            }
                           
                           
                        
                      
                      
                       
                    
                
        
                 if(myStack.size()==0)
                            break;
             
            }else {
                                if(getTotalcost(lastone)+herustic(lastone, Goalstate)<min){
                                    min=getTotalcost(lastone)+herustic(lastone, Goalstate);
                                }
                            }
            }else{
               // Fringe.poll();
                //printanswer(lastone);
                System.out.println("Alghorithm : "+ "iDA*");
              int b=  printtheanswer(lastone,"iDA*");
                end = System.currentTimeMillis();
                long time=end-mill;
               // time=time / 1000;
               System.out.println("Number of generated nodes "+ nodesgenerated);
           
               System.out.println("Maximom nodes in Ram"+" "+ramnodes);
                System.out.println("Time "+ ":"+time+"mill");
                GoalFound=true;
                break;
                //print the answer
            }
        }
            //System.out.println("hi");
            if(GoalFound==true){
                break;
            }
         cutoff=min;
        myStack.add(instationstate);
        }
         }
         
               catch (FileNotFoundException e) {
        e.printStackTrace();
    } 
    }else if(alghorithm==9){    //RBFS
         
         
                long mill;
                boolean GoalFound=false;
                
           long end;
        int n=0,m=0;
         File file = new File("in.txt");
           mill = System.currentTimeMillis();
        
         try {

        Scanner sc = new Scanner(file);
       
        while (sc.hasNextInt()) {
             n = sc.nextInt();
             m=sc.nextInt();
        }
         
        State instationstate=new State(n, m);
         instationstate.setDeapth(0);
        State Goalstate=new State(n,m);
        int i=0;
        int j=0;
        while (sc.hasNextLine()){
            String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                instationstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
        //sc.nextLine();
        i=0;
        while(sc.hasNextLine()){
            
            j=0;
             String token=sc.next();
            char[] myChar = token.toCharArray();
            for( j=0;j<myChar.length;j++){
                Goalstate.boeard[i][j]=myChar[j];
            }
            i++;
            if(i==n)
                break;
        }
       sc.close();
           instationstate.setDeapth(0);
          
        ArrayList<State> nextstates=new ArrayList<>();
           PriorityQueue<State> Fringe =new PriorityQueue<>(9999999, (State o1, State o2) -> {
               
               if(o1.fval> o2.fval){
                   return 1;
               }else if(o1.fval < o2.fval){
                  return -1 ;
               }else{
                   return 0;
               }
              
               
           });
        Fringe.add(instationstate);
        Set <State> visitedlist=new HashSet<State>();
        visitedlist.add(instationstate);
        int generatednodes=1;
        int flimit=99999;
        int ramnodes=1;
       while(!GoalFound){
        while( Fringe.size()!=0){
            
            int result=compare(Goalstate, Fringe.peek());
           // System.out.println("hi");
            State lastone=Fringe.peek();
            if(result==0){
                State father=Fringe.peek();
                
            nextstates=(ArrayList<State>) sucsasor(Fringe.poll());
            visitedlist.add(father);
            
            if(Fringe.peek()!=null){
             
             flimit=getTotalcost(Fringe.peek())+herustic(Fringe.peek(), Goalstate);
            }
           

            generatednodes=generatednodes+nextstates.size();
               ramnodes=Math.max(Fringe.size()+visitedlist.size(),ramnodes);
           int miniom=99999;
         
        for(int v=0;v<nextstates.size();v++){
        
          
            State now=nextstates.get(v);
            if(!visitedlist.contains(now)){
                
               if(!Fringe.contains(now)){
                               now.setParent(father);
                               now.setFval(herustic(now, Goalstate)+getTotalcost(now));
                               if(herustic(now, Goalstate)+getTotalcost(now)< miniom){
                                   miniom=herustic(now, Goalstate)+getTotalcost(now);
                                
                                   Fringe.add(now);
                               }
                              
                       
            }
            
        }
       
        }
       
       if(miniom > flimit){
           father.setFval(miniom);
           Fringe.add(father);
       }
            }else{
               // Fringe.poll();
                //printanswer(lastone);
                System.out.println("Alghorithm : "+ "RBFS");
              int b=  printtheanswer(lastone,"RBFS");
                end = System.currentTimeMillis();
                long time=end-mill;
               // time=time / 1000;
               System.out.println("Number of generated nodes "+ generatednodes);
           
               System.out.println("Maximom nodes in Ram"+" "+ramnodes);
                System.out.println("Time "+ ":"+time+"mill");
                GoalFound=true;
                break;
                //print the answer
            }
        }
        
       if(GoalFound==true) break;
          
       
        
    }
        
    }  
            
               catch (FileNotFoundException e) {
        e.printStackTrace();
    } 

    }
       
    }
    
}
       
    
    
    

    

