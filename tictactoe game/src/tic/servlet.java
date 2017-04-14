package tic;

import java.awt.TextArea;  
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class servlet
 */
public class servlet extends HttpServlet {  
	private static final long serialVersionUID = 1L;
	public static HttpSession ss;
	public static int x=1;
    /** 
     * @see HttpServlet#HttpServlet()
     */
	static TreeSet<Integer> ts=new TreeSet<Integer>();
	static TreeSet<Integer> tm=new TreeSet<Integer>();
    static TreeSet<Integer> al=new TreeSet<Integer>(); 
	public servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		String t="";
		StringBuffer q=new StringBuffer("");
		int k=0;
		try
		{
		if(request.getParameter("log").equals("SIGN IN"))
		{
		String d=request.getParameter("name");
		String h=request.getParameter("pass");
		System.out.println(d+" "+h);
		t=read(d,h);
		System.out.println(t);
		}
		else if(request.getParameter("log").equals("admin"))
		{
			System.out.println("ss");
			System.out.println("admin"+request.getParameter("admin"));
			boolean gh=admin(request.getParameter("admin"),request.getParameter("password"));
			if(gh==true)
				response.sendRedirect("admin.html");
		}
		else if(request.getParameter("log").equals("Submit"))
		{
			Connection cb=util();
			PreparedStatement pq=cb.prepareStatement("insert into program values(?,?,?)");
			pq.setInt(1,Integer.parseInt(request.getParameter("id")));
			pq.setString(2,request.getParameter("question"));
			pq.setString(3,request.getParameter("answer"));
			int cz=pq.executeUpdate();
			System.out.println("insert"+cz);
			if(cz==1)
				response.sendRedirect("home.html");
		}
		else if(request.getParameter("log").equals("Edit"))
		{
			Connection sd=util(); 
			PreparedStatement pp=sd.prepareStatement("update program set question=? where id=? ");
			PreparedStatement pw=sd.prepareStatement("update program set answer=? where id=? ");
			pp.setString(1,request.getParameter("question"));
			pw.setString(1,request.getParameter("answer"));
			pp.setInt(2,Integer.parseInt(request.getParameter("id")));
			pw.setInt(2,Integer.parseInt(request.getParameter("id")));
			int rt=pp.executeUpdate(),lk=pw.executeUpdate();
			System.out.println("update"+rt);
			if(rt==1&&lk==1)
				response.sendRedirect("home.html");
		}
		else if(request.getParameter("log").equals("reset"))
		{ 
			x=1;
			truncate();
			tm.clear();
			ts.clear();
			al.clear();
			session.setAttribute("ans","");
			session.setAttribute("ss1", "");
			session.setAttribute("ss2", "");
			session.setAttribute("code","");
			response.sendRedirect("compiler.jsp");
		}
		else if(request.getParameter("log").equals("register"))
		{
		String v=request.getParameter("user");
		String y=request.getParameter("pasw");
		String z=request.getParameter("pasww");
		String za=request.getParameter("email");
		System.out.println(v+" "+y+z+" "+za);
		if(!y.contains(z))
		   {
		 	response.sendRedirect("register.html");
		    return;
		   }
		int z1=insert(v,y,za);
		 if(z1==1)
		    {   
			 try{
			      response.sendRedirect("login.html");
		          return;
		        }
		  	  catch(Exception e)
		        {
			     e.printStackTrace();
		        }
		    }
		}
		else if(request.getParameter("log").equals("Run"))
		  {
			String f[]=new String[10];
			String f1[]=new String[10];
			String v="",o="",md="",me="",cv="",mm="";
			String df=request.getParameter("hid");
			if(request.getParameter("txt").isEmpty()&&request.getParameter("file").isEmpty())
					response.sendRedirect("compiler.jsp");
			if(request.getParameter("txt").isEmpty()&&!request.getParameter("file").isEmpty())
			{
				String fg=request.getParameter("file"),m="";
				//String[] b=fg.split(".");
				System.out.println("dd"+fg);
				File fr=new File("C:\\eclipse\\save.java");
				FileWriter fu=new FileWriter(fr);
				BufferedReader bf=new BufferedReader(new FileReader(fg));
				String lin="",p="";
				do{
				 lin=bf.readLine();
				 if(lin==null)
					 break;
				 if(lin.contains("class"))
				 {
					 System.out.println("yesss"); 
					 fu.write("public class save\n");
				 }
				 else
				 {
				 fu.write(lin);
				 fu.write("\n");
				 }
				System.out.println(lin);
				}while(lin!=null);
				fu.close();
				cv=run("javac save.java");
				if(cv.length()==0)
				{
				o=run("java save");
				}
				System.out.println("oo"+o+" "+o);
	            session.setAttribute("ans",o);
	            md=answer(Integer.parseInt(df));
				System.out.println(md);
				System.out.println("upload"+o);
			}
			else if(!request.getParameter("txt").isEmpty()&&request.getParameter("file").isEmpty())
			{ 
				String dd="";
				mm=request.getParameter("txt");
			File fd=new File("C:\\eclipse\\save.java");
			FileWriter ff=new FileWriter(fd);
			ff.write(request.getParameter("txt"));
			ff.close();
			cv=run("javac save.java");
			System.out.println("comp"+cv);
			if(cv.length()==0)
			{
			v=run("java save");
			}
			System.out.println(cv.length());
			System.out.println("er"+cv);
			System.out.println("vv"+v);
			//f1=v.split(",");
			//session.setAttribute("ans",v);
			me=answer(Integer.parseInt(df));
			System.out.println("type"+v);
			System.out.println(me);
			}
			if(v.equals(me)&&!v.isEmpty()&&!me.isEmpty())
			{  
				
				session.setAttribute("ans","");
				x++;
				tm.add(Integer.parseInt(df));
				response.sendRedirect("sample1.jsp");
			}
			else if(o.equals(md)&&!o.isEmpty()&&!md.isEmpty())
			{
				
				session.setAttribute("ans","");
				x++;
				tm.add(Integer.parseInt(df));
				response.sendRedirect("sample1.jsp");
			}
			else
			{   
				session.setAttribute("code",mm);
				session.setAttribute("ans",cv);
				//cv="";
				response.sendRedirect("compiler.jsp");
			}
		  }
	  }
	  catch(Exception e)
		{
		  	int zq=0;
			if(request.getParameter("val")!=null)
			{String b=request.getParameter("val");
			System.out.println("buton"+request.getParameter("val"));
			if(al.add((Integer.parseInt(b)))){
				//al.add((Integer.parseInt(b)));
				ts.add(Integer.parseInt(b));
		    ius(Integer.parseInt(b));
			//}
			session.setAttribute("code","");
			PrintWriter out=response.getWriter();
			Random rt=new Random();
			//if(ts.size()==9)
				//ts.clear();
			while(true)
			{
			 zq=rt.nextInt(8)+1;
			 System.out.println("ll"+zq);
			 if(ts.add(zq))
			 {  al.add(zq);
				 System.out.println("cpuu"+zq+" "+ts);
			 iuser(zq+"");
			 //out.print(zq);
			 System.out.println("here");
			 break;
			 }
			 if(ts.size()==9)
			 {   ts.clear();
			     al.clear();
				 break;
			 }
			}
			System.out.println(ts);
			String v=fetch();
			session.setAttribute("ss2",v);
			//System.out.println("full"+v);
			String g=fetch1();
			session.setAttribute("ss1",g);
			int z=0;
			//System.out.println(v);
			//System.out.println(g);
			//res(request,response,g,v);
			//String j=fetch2();
			int x1=0,n=0,w=0;
			String pv="";
			String p[]={"123","132","231","213","312","321","456","465","546","564","645","654","789","798","879","897","978","987","147","174","714","741","417","471","258","285","528","582","825","852","369","396","639","693","936","963","195","159","519","591","915","951","357","375","537","573","735","753"};
			System.out.println("user"+g+"  "+"cpu"+v);
			ArrayList ag=new ArrayList();
			for(int j=0;j<g.length();j++)
				ag.add(g.charAt(j));
			ArrayList av=new ArrayList();
			for(int j=0;j<v.length();j++)
				av.add(v.charAt(j));
			for(int i=0;i<p.length;i++)
			{
				for(int m=0;m<p[i].length();m++)
				{
					if(ag.contains(p[i].charAt(m)))
						n++;
					else if(av.contains(p[i].charAt(m)))
						z++;
					    
				}
				if(n==3)
				{
					x=0;
					tm.clear();
					ts.clear();
					q=new StringBuffer("USER WON");
					//System.out.println("user11");
					ag.clear();
					x1++;
				    truncate();
					break;
				}
				else if(z==3)
				{   
					x=0;
					ts.clear();
					tm.clear();
					x1++;
					truncate();
					q=new StringBuffer("CPU WON");
					System.out.println("cpu11");
				    av.clear();
					break;
				}
				n=0;
				z=0;
				w++;
			}
			System.out.println("no "+w);
			if((v.length()+g.length())==9&&x1==0&&w==48)
			    {
				x=0;
				truncate();
				al.clear();
				ts.clear();
				tm.clear();
				System.out.println("draw");
				q=new StringBuffer("DRAW");
			    }
			out.print(zq);
			out.print(q);
			}
		}else 
			response.sendRedirect("sample1.jsp");
		}
		if(t.equals("true"))
		    {session.setAttribute("code","");
			session.setAttribute("ans","");
			response.sendRedirect("compiler.jsp");
		    ts.clear();
			return;
		    }
		else if(t.equals("false")){
			response.sendRedirect("login.html");
			return;
			}
	}
	    public static String run(String command)
	    {
	    		String b="",zx="",p="",q="";
				Process po;
				try {
					po = Runtime.getRuntime().exec(command);
					b=print(command+" stdout:",po.getInputStream());
					zx=print(command+" stderr:",po.getErrorStream());
					//System.out.println("op:"+po.getOutputStream());
					//System.out.println("error "+po.getErrorStream());
                    //Thread.sleep(5000);
                    //po.destroy();
					//po.waitFor();
					//System.out.println(command+"exitval()"+po.exitValue());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String n=b+zx; 
				return n;
	    }
	    public static String print(String name,InputStream ins)
	    {
	    	String h="";
	    	StringBuffer st=new StringBuffer();
	    	BufferedReader bv=new BufferedReader(new InputStreamReader(ins));
			try {
				String b;
				while((h=bv.readLine())!=null)
				{
					st.append(h);
					//System.out.println(name+" "+h);
				}
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("output:"+st.toString()+";");
			return st.toString();
	    }
		public static Connection util()
		{
			Connection cp=null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				cp=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","hr");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("here");
				e.printStackTrace();
			}
			return cp;
		}
		public static int insert(String x,String y,String bv)
		{
			System.out.println("asd");
			int z=0;
			Connection cv=util();
			try {
				PreparedStatement po=cv.prepareStatement("insert into login values(?,?,?)");
				po.setString(1,x);
				po.setString(2,y);
				po.setString(3,bv);
				 z=po.executeUpdate();
				 System.out.println("inser"+z);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return z;
		}
		public static String read(String f,String g)
		{
			Connection cb=util();
			String j="";
			try {
				PreparedStatement pi=cb.prepareStatement("select password from login where username=?");
				pi.setString(1,f);
				//pi.execute();
				ResultSet rs=pi.executeQuery();
				while(rs.next())
				{
					j+=rs.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}
			if(j.equals(g))
				return "true";
			else
				return "false";
		}
		public static String ius(int comp)
		{
			int x2=0;
			Connection cp=util();
			try {
				PreparedStatement pr=cp.prepareStatement("insert into users values(?)");
				pr.setInt(1,comp);
				x2=pr.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(x2>0)
				return "success";
			else
				return "false";
		}

		public static String iuser(String user)
		{
			int x1=0;
			Connection cv=util();
			try {
				PreparedStatement ps=cv.prepareStatement("insert into xo values(?)");
				ps.setInt(1,Integer.parseInt(user));
				 x1=ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return "fail";
			}
			if(x1>0)
			{  
				return "success";
			}
			else
			{ 
				return "false";
			}
		}
		public static String fetch()
		{
			String d="",dt="";
			Connection cx=util();
			try {
				PreparedStatement po=cx.prepareStatement("select * from xo");
				ResultSet rt=po.executeQuery();
				d="";
				while(rt.next())
				{
					d+=rt.getInt(1)+"";	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  return d;
		}
		public static String fetch1()
		{
			String d1="";
			Connection cx=util();
			try {
				PreparedStatement po=cx.prepareStatement("select * from users");
				ResultSet rt=po.executeQuery();
				while(rt.next())
				{
					d1+=rt.getInt(1)+"";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return d1;
		}
		public static boolean admin(String unam,String j)
		{
			String d1="";
			Connection cx=util();
			try {
				PreparedStatement po=cx.prepareStatement("select password from admin where uname=?");
				po.setString(1,unam);
				ResultSet rt=po.executeQuery();
				while(rt.next())
				{
					d1=rt.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("pass"+d1);
			if(d1.equals(j))
				return true;
			else 
				return false;
		}
		public String answer(int id)
		{
			String sp="";
			Connection cx=util();
			try {
				PreparedStatement po=cx.prepareStatement("select answer from program where id=?");
				po.setInt(1,id);
				ResultSet rt=po.executeQuery();
				while(rt.next())
				{
					sp=rt.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sp;
		}
		public String question()
		{
			
			String sp="",sf="";
			Connection cx=util();
			try {
				//x++;
				/*System.out.println(tm+" "+x);
				while(true)
				{
				if(tm.contains(x))
					x++;
				else if(x==6)
					x=1;
				else 
					break;
				}*/
				if(x==6||tm.size()==5)
					x=1;
				PreparedStatement po=cx.prepareStatement("select id,question from program where id=?");
				po.setInt(1,x);
				System.out.println("xx"+x);
				ResultSet rt=po.executeQuery();
				if(rt.next())
				{
					sp=rt.getString(1);
					sf=rt.getString(2);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sp+sf;
		}
		 public static void truncate()
		    {
		    	Connection cn=util();
		    	try {
					PreparedStatement po=cn.prepareStatement("truncate table xo");
					boolean b1=po.execute();
					PreparedStatement pi=cn.prepareStatement("truncate table users");
					boolean b2=pi.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
	}

