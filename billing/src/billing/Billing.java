/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing;

/**
 *
 * @author Go
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

public class Billing {
Graphics g; 
int i;
String[] parties=new String[100];
public String[] party(){
    return parties;
}
public void add(String s){
    for(int i=0;i<100;i++){
        if(parties[i]==null)
            break;
    }
    parties[i]=s;
}
public void Billingjob(String[] a1,String a2, String a4, String[] details,int dat, int month, int year) throws MalformedURLException, IOException, PrintException, InterruptedException{

   int amt=Integer.parseInt(a2);
   if(amt>=1000){
       a2=a2.substring(0, a2.length()-3)+","+a2.subSequence(a2.length()-3, a2.length());
   }
   int tax1=(amt*14)/100;
   int sb1=(int) ((amt*0.5)/100);
    int kk1=(int) ((amt*0.5)/100);
   String tax=String.valueOf(tax1);
   if(tax1>=1000){
       tax=tax.substring(0, tax.length()-3)+","+tax.subSequence(tax.length()-3, tax.length());
   }
   String sb=String.valueOf(sb1);
   if(sb1>=1000){
       sb=sb.substring(0, sb.length()-3)+","+sb.subSequence(sb.length()-3, sb.length());
   }
       
   String kk=String.valueOf(kk1);
   if(kk1>=1000){
       kk=kk.substring(0, kk.length()-3)+","+kk.subSequence(kk.length()-3, kk.length());
   }  
   Number obj=new Number();
    
      String ab=obj.convertNumberToWords(amt+tax1+sb1+kk1);
    ab+=" Only.";
    String a3;
    String[] dd={"","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    String[] mm={"","01","02","03","04","05","06","07","08","09","10","11","12"};
    String[] yy={"","2020","2019","2018","2017", "2016","2015", "2014", "2013", "2012", "2011","2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001"}; 
    
    if(dat!=0){
      a3=dd[dat]+"/"+mm[month]+"/"+yy[year];
    }
    else{
    java.util.Date date = new java.util.Date();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    a3=dateFormat.format(date); //2016/11/16 12:08:43
    }
    String a=(String)System.getProperty("user.dir")+"\\images\\formatfinal.jpg";
    final BufferedImage image = ImageIO.read(new URL("file:\\"+a));
    g = image.getGraphics();
    g.setFont(new Font("Calibri", Font.BOLD, 50));g.setColor(Color.BLACK);
    g.drawString(a4, 573, 565);//Bill No.
    
    
    g.drawString(a2, 2017, 1161);//Amount
    g.drawString(a3, 1874, 567);//Date
    g.drawString(ab, 503, 1984);//Amount in Words.
   
//    g.drawString(parties[j][0], 545, 556);
    int i=0;
    /*int x=441,y=1383;
    for(int i=0;i<a1.length();i++){
    if(x>1873){
        y+=125;
    }*/
    
    int x=393,y=1151;
    for(i=0;i<a1.length;i++){
     g.drawString(a1[i],x, y);//Fee Rendered
     y+=100;
    }
    g.drawString(tax, 2017, 1705);
    g.drawString(sb, 2017, 1795);
g.drawString(kk, 2017, 1840);  
//Tax
x=364;y=688;
    for(i=0;i<details.length;i++){
     g.drawString(details[i],x, y);//Fee Rendered
     y+=100;
    }
int ta=amt+tax1+sb1+kk1;
String taa=String.valueOf(ta);
if(ta>=1000){
       taa=taa.substring(0, taa.length()-3)+","+taa.subSequence(taa.length()-3, taa.length());
   }  
    g.drawString(taa, 2000, 1952);//Total Amount
 
  /*  DatabaseParties e1=new DatabaseParties();//
    NewJFrame o=new NewJFrame();
    int i1=o.party();
    String as[] =e1.fetchNames();
   String[] details = e1.fetchDetails(as[i1]);
    x=441;y=1383;
    for(i=0;i<details.length;i++){
     g.drawString(details[i],x, y);//Party Details
     y+=100;
    }
   */
    
    
    
    
    
    
    
    
    
    g.dispose();
 //BufferedImage img=obj.enlarge(image,2480,3508);
    ImageIO.write(image, "jpg", new File("test1.jpg"));
 /*         
    
   PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
    pras.add(new Copies(1));
    PrintService pss[] = PrintServiceLookup.
    
    if (pss.length == 0)
      throw new RuntimeException("No printer services available.");
    PrintService ps = pss[0];
    System.out.println("Printing to " + ps);
    DocPrintJob job = ps.createPrintJob();
    FileInputStream fin = new FileInputStream(System.getProperty("user.dir")+"\\test1.jpg");
    Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.JPEG, null);
    job.print(doc, pras);
    fin.close();
*/   
 InputStream is =
new BufferedInputStream(new FileInputStream(System.getProperty("user.dir")+"\\test1.jpg"));
  // create a PDF doc flavor
  DocFlavor flavor = DocFlavor.INPUT_STREAM.JPEG;
  // Locate the default print service for this environment.
  HashPrintRequestAttributeSet hh=new HashPrintRequestAttributeSet();
 hh.add(new MediaPrintableArea(0f,0f,0.00000001f,0.000000001f,MediaPrintableArea.INCH));
 PrintRequestAttributeSet pras =new HashPrintRequestAttributeSet();
 
  PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
    PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
    PrintService service =ServiceUI.printDialog(null, 0, 0,printService, defaultService, DocFlavor.INPUT_STREAM.JPEG, hh);// Create and return a PrintJob capable of handling data from  
  
// any of the supported document flavors.
  DocPrintJob printJob = service.createPrintJob();  
// register a listener to get notified when the job is complete
  printJob.addPrintJobListener(new JobCompleteMonitor());  
//printJob.
// Construct a SimpleDoc with the specified
  // print data, doc flavor and doc attribute set.
  Doc doc = new SimpleDoc(is, flavor, null);
  // Print a document with the specified job attributes.
 

  printJob.print(doc, hh);

}
    private static class JobCompleteMonitor extends PrintJobAdapter {
        @Override
        public void printJobCompleted(PrintJobEvent jobEvent) {
            System.out.println("Job completed");
            System.exit(0);
          }
    }

   public static void main(String[] args) throws Exception {
   }
}


