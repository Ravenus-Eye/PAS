package norimaDB;

import java.time.LocalDate;

public class RatingEngine extends Vehicle{
	
	public void calculatePremium(double vp, int year) {
		int dlx = LocalDate.now().getYear() - year;
	    double vpf = 0;
	    if      (dlx <  1) {vpf = .010;} 
	    else if (dlx <  3) {vpf = .008;} 
	    else if (dlx <  5) {vpf = .007;} 
	    else if (dlx < 10) {vpf = .006;} 
	    else if (dlx < 15) {vpf = .004;} 
	    else if (dlx < 20) {vpf = .002;} 
	    else if (dlx < 40) {vpf = .001;}
		premiumCharge = (vp * vpf) + ((vp/100)/dlx);
	}

}
