package usecase



object OverLapSolution {
  
  def main(args: Array[String]): Unit = {
    val zipCodeRangeList : List[ZipCodeRange]  = generateData()
    val normalMap : scala.collection.mutable.SortedMap[Long,ZipCodeRange] = scala.collection.mutable.SortedMap.empty[Long,ZipCodeRange]
    val controlMap : scala.collection.mutable.SortedMap[Long,ZipCodeRange] = scala.collection.mutable.SortedMap.empty[Long,ZipCodeRange](normalMap.ordering.reverse)

    
    val outputZipList : scala.collection.mutable.Set[ZipCodeRange] = scala.collection.mutable.Set.empty[ZipCodeRange]
    for(zipCodeRange <- zipCodeRangeList.iterator){
      controlMap += ((String.valueOf(zipCodeRange.endZip-zipCodeRange.startZip)+String.valueOf(System.currentTimeMillis())).toLong -> zipCodeRange)
    }

    controlMap.foreach(f => {
      if(controlMap.contains(f._1)){
           val superStart : Long = controlMap.get(f._1).get.startZip
           val superEnd : Long = controlMap.get(f._1).get.endZip
           val rangeList : scala.collection.mutable.Set[Long] = scala.collection.mutable.Set.empty[Long]
           rangeList += superStart 
           rangeList += superEnd
           controlMap.foreach(x => {
            if(controlMap.contains(x._1)){ 
              val subStart : Long = controlMap.get(x._1).get.startZip
              val subEnd : Long = controlMap.get(x._1).get.endZip
              if(subEnd >= superEnd && subStart <= superEnd) {
                          rangeList += subEnd
                          controlMap -= (x._1)
                      }else if(subStart <= superStart && subEnd >= superStart) {
                          rangeList += subStart
                          controlMap -= (x._1)
                      }else if(subStart >= superStart && subEnd <= superEnd) {
                          controlMap -= (x._1)
                      }else{}
            }  
           })           
           outputZipList += new ZipCodeRange(rangeList.min,rangeList.max)
      }
    })    
    outputZipList.foreach(f => println(f.startZip+" - "+f.endZip))        
  }

  
  def generateData(): List[ZipCodeRange] = {
    val zipCodeRangeList : scala.collection.mutable.ListBuffer[ZipCodeRange] = scala.collection.mutable.ListBuffer.empty[ZipCodeRange]
    // add element
    zipCodeRangeList.toList
  }
  
}