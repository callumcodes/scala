/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.concurrent



import scala.util.Timeout



/** Promise is an object which can be completed with a value or failed
 *  with an exception.
 *
 *  A promise is assigned a timeout when created. After the timeout expires,
 *  the promise will be failed with a TimeoutException.
 *
 *  @define promiseCompletion
 *  If the promise has already been fulfilled, failed or has timed out,
 *  calling this method will throw an IllegalStateException.
 */
trait Promise[T] {
  
  /** Future containing the value of this promise.
   */
  def future: Future[T]
  
  /** Completes the promise with a value.
   *  
   *  @param value    The value to complete the promise with.
   *  
   *  $promiseCompletion
   */
  def fulfill(value: T): Unit
  
  /** Completes the promise with an exception.
   *  
   *  @param t        The throwable to complete the promise with.
   *  
   *  $promiseCompletion
   */
  def break(t: Throwable): Unit

  /** Wraps a `Throwable` in an `ExecutionException` if necessary.
   *
   *  $allowedThrowables
   */
  protected def wrap(t: Throwable): Throwable = t match {
    case t: Throwable if isFutureThrowable(t) => t
    case _ => new ExecutionException(t)
  }

}



object Promise {
  /*
  /**
   * Creates a non-completed, new, Promise with the supplied timeout in milliseconds
   */
  def apply[A](timeout: Timeout)(implicit dispatcher: MessageDispatcher): Promise[A] = DefaultPromise[A](timeout)

  /**
   * Creates a non-completed, new, Promise with the default timeout (akka.actor.timeout in conf)
   */
  def apply[A]()(implicit dispatcher: MessageDispatcher, timeout: Timeout): Promise[A] = apply(timeout)
  */
}
