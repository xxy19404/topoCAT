/*
 * Created October 2012
 * 
 * This file is part of Topological Data Analysis
 * edu.duke.math.tda
 * Copyright (c) 2012-2014 by John Harer
 * All rights reserved.
 * 
 * License Info:
 * 
 * 
 */

package edu.duke.math.tda.structures.metric;

import edu.duke.math.tda.structures.PointI;
import edu.duke.math.tda.structures.PointRn;
import edu.duke.math.tda.utility.TDA;
import edu.duke.math.tda.utility.StringUtil;
import edu.duke.math.tda.utility.errorhandling.TdaException;

/**
 * Subclass of Metric class, implementing the classic L2-metric
 * 
 * <p><strong>Details:</strong> <br>
 * 
 * Computes the L2-distance between 2 points
 *  *  
 * <p><strong>Change History:</strong> <br>
 * Created October 2012
 * Modified Feb 2013, to use PointI, and compute distance after type-checking
 * and -casting to PointRn
 * 
 * 
 * @author Jurgen Sladeczek (hjs) <br>
 * For the latest info, please visit www.math.duke.edu.
 */

public class L2Metric extends Metric {

	@Override
	public double computeDistance( 
			final PointI _p1, final PointI _p2 ) throws Exception {

		PointRn p1;
		PointRn p2;

		// Implement for each specific class that implements the metricI interface, and
		// for which we can compute the distance
		if ( StringUtil.getClassName( _p1 ).equals( "PointRn" )
				&& StringUtil.getClassName( _p2 ).equals( "PointRn" ) ) {

			// it's valid to cast to PointRn, so we compute the distance
			p1 = ( PointRn ) _p1;
			p2 = ( PointRn ) _p2;
			
			double distance = 0;
			int dimension1 = p1.getDimension();
			int dimension2 = p2.getDimension();
			
			if ( dimension1 != dimension2 ) {
				
				throw new TdaException( 
				        TDA.ERROR_APP_USERINPUT, 
                        "(L2metric.computeDistance) " +
				        "Two provided points in Rn, p1 (=" + p1.toString() +
                        ") and p2 (= " + p2.toString() + ") " +
				        "have different dimension." );
			}
		
			for ( int i=0; i< dimension1; i++ ) {
				
				distance += ( p1.getCoordinates()[ i ] - p2.getCoordinates()[ i ] ) *
						( p1.getCoordinates()[ i ] - p2.getCoordinates()[ i ] );
			}
			
			return Math.sqrt( distance );
		}
		else {
			
			// throw an exception
			throw new TdaException( 
			        TDA.ERROR_APP_DEV, 
                    "(L2metric.computeDistance) " +
			        "Two provided points p1 and p2 cannot be cast to specific classes " +
			        "for which we can compute the L2 distance." );
		}
	}

	@Override
	public double compute( 
			final PointI _p ) throws Exception {

		PointRn p;

		// Implement for each specific class that implements the metricI interface, and
		// for which we can compute the distance
		if ( StringUtil.getClassName( _p ).equals( "PointRn" ) ) {

			// it's valid to cast to PointRn, so we compute the distance
			p = ( PointRn ) _p;
			
			double distance = 0;
			int dimension = p.getDimension();
		
			for ( int i=0; i< dimension; i++ ) {
				
				distance += p.getCoordinates()[ i ] * p.getCoordinates()[ i ];
			}
			
			return Math.sqrt( distance );
		}
		else {
			
			// throw an exception
			throw new TdaException( 
			        TDA.ERROR_APP_DEV, 
                    "(L2metric.computeDistance) " +
			        "Two provided point p cannot be cast to specific classes " +
			        "for which we can compute the L2 distance." );
		}
	}
}
