

class QueueModel(n: Int) {

  val inner = new java.util.concurrent.LinkedBlockingQueue[Int](n)

  def send(data: Int): Unit = {
    inner.put(data)
  }

  def receive(): Int = {
    if (inner.isEmpty()) {
      throw new RuntimeException("Queue is empty")
    }
    inner.take()
  }

}
