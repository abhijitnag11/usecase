package usecase

class ZipCodeRange(start :Long, end : Long) extends Serializable {
  var startZip : Long = start
  var endZip : Long = end  
}