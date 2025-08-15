import tester.Tester;

interface ILoIntegers {
	boolean checkRequirements();
	boolean thereIsAnEvenNumber();
	boolean thereIsAPositiveOddNumber();
boolean thereIsANumberBetween5and10();
}

class ConsLoIntegers implements ILoIntegers {
	int first;
	ILoIntegers rest;

	ConsLoIntegers(int first, ILoIntegers rest) {
		this.first = first;
		this.rest = rest;
	}

	public boolean checkRequirements() {
		return thereIsAnEvenNumber() && thereIsAPositiveOddNumber() && thereIsANumberBetween5and10();
	}

	@Override
	public boolean thereIsAnEvenNumber() {
		if (first % 2 == 0) {
			return true;
		} else {
			return rest.thereIsAnEvenNumber();
		}
	}

	@Override
	public boolean thereIsAPositiveOddNumber() {
		if (first % 2 != 0 && first > 0) {
			return true;
		} else {
			return rest.thereIsAPositiveOddNumber();
		}
	}

	@Override
	public boolean thereIsANumberBetween5and10() {
		if (first >= 5 && first <= 10) {
			return true;
		} else {
			return rest.thereIsANumberBetween5and10();
		}
	}
}

class EmptyLoIntegers implements ILoIntegers {
	public boolean checkRequirements() {
		return false;
	}

	@Override
	public boolean thereIsAnEvenNumber() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean thereIsAPositiveOddNumber() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean thereIsANumberBetween5and10() {
		// TODO Auto-generated method stub
		return false;
	}
}

class ExamplesILoIntegers {
	ILoIntegers eli = new EmptyLoIntegers();
	ILoIntegers list1 = new ConsLoIntegers(6, new ConsLoIntegers(5, eli));
	ILoIntegers list2 = new ConsLoIntegers(4, new ConsLoIntegers(3, eli));
	
	boolean testCheckRequirements(Tester t) {
		return t.checkExpect(list1.checkRequirements(), true) 
				&& t.checkExpect(list2.checkRequirements(), false);
	}
}