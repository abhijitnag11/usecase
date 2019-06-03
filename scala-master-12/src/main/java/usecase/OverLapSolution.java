package usecase;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class OverLapSolution {

	public static void main(String[] args) {
		
	    List <ZipCodeRangePojo> inputZipList = generateData();
	    List <ZipCodeRangePojo> outputZipList = new ArrayList<ZipCodeRangePojo>();
	    ConcurrentSkipListMap<Long,ZipCodeRangePojo> controlMap = new ConcurrentSkipListMap<Long,ZipCodeRangePojo>(Comparator.reverseOrder());
	    for(ZipCodeRangePojo zipCodeRange : inputZipList) {
	    	controlMap.put(Long.valueOf(String.valueOf(zipCodeRange.endZip-zipCodeRange.startZip)+String.valueOf(System.currentTimeMillis())),zipCodeRange);
	    }
	    
	    
		for(Entry<Long,ZipCodeRangePojo> superDataSet : controlMap.entrySet()) {
		     if(controlMap.containsKey(superDataSet.getKey())){
		        long superStart = controlMap.get(superDataSet.getKey()).startZip;
		        long superEnd = controlMap.get(superDataSet.getKey()).endZip;
		        Set<Long> rangeList = new TreeSet<Long>();
		        rangeList.add(superStart);rangeList.add(superEnd);

		            for(Entry<Long,ZipCodeRangePojo> subDataSet : controlMap.entrySet()) {
		              if(!superDataSet.getKey().equals(subDataSet.getKey())) {
		                  long subStart = controlMap.get(subDataSet.getKey()).startZip;
		                  long subEnd = controlMap.get(subDataSet.getKey()).endZip;
		                  // Check the Range
		                  if(subEnd >= superEnd && subStart <= superEnd) {
		                      rangeList.add(subEnd);
		                      controlMap.remove(subDataSet.getKey());
		                  }else if(subStart <= superStart && subEnd >= superStart) {
		                      rangeList.add(subStart);
		                      controlMap.remove(subDataSet.getKey());
		                  }else if(subStart >= superStart && subEnd <= superEnd) {
		                      controlMap.remove(subDataSet.getKey());
		                  }else
		                      ;
		                  System.out.println("Size of controlMap "+controlMap.size());
		              }
		            }
		            outputZipList.add(new ZipCodeRangePojo(Collections.min(rangeList),Collections.max(rangeList)));
	}
	// Final ranges are
		     for(ZipCodeRangePojo out : outputZipList) {
		    	 System.out.println(out.startZip+"  -- "+out.endZip);
		     }
 }
}

	private static List<ZipCodeRangePojo> generateData(){
		List<ZipCodeRangePojo> l = new ArrayList<ZipCodeRangePojo>();
		//....
		return l;
	}
	
}	
