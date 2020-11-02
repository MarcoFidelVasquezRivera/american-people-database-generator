package customExceptions;

@SuppressWarnings("serial")
public class ElementAlreadyExistException extends Exception{

	public ElementAlreadyExistException() {
		super("The element with this key already exists");
	}
	
	@Override
	public String toString() {
		return super.getMessage();
	}
}
