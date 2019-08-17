object MarkerTraitApp extends App{
  trait Contract

  class TradeContract extends Contract{
    val id: String = "Empty"
  }

  class LoanContract(id: String, data: String) extends Contract{

  }



}
