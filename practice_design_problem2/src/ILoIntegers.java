import tester.Tester;

interface ILoIntegers {
	boolean checkRequirements();

}

class ConsLoIntegers implements ILoIntegers {
	int first;
	ILoIntegers rest;

	ConsLoIntegers(int first, ILoIntegers rest) {
		this.first = first;
		this.rest = rest;
	}

	public boolean checkRequirements() {
		return false;
	}
}

class EmptyLoIntegers implements ILoIntegers {
	public boolean checkRequirements() {
		return false;
	}
}

class ExamplesILoIntegers {
	ILoIntegers eli = new EmptyLoIntegers();
	ILoIntegers list1 = new ConsLoIntegers(6, new ConsLoIntegers(5, eli));
	ILoIntegers list2 = new ConsLoIntegers(4, new ConsLoIntegers(3, eli));
	
	boolean testCheckRequirements(Tester t) {
		return t.checkExpect(list1.checkRequirements(), true) 
				&& t.checkExpect(list2, false);
	}
}