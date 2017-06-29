package dms.manger.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
	       Long time=new Long(560705990);
	       String d = format.format(time);
	       Date date = null;
		try {
			date = format.parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	       System.out.println("Format To String(Date):"+d);

	       System.out.println("Format To Date:"+date);
	}

}
