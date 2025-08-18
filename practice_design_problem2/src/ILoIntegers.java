import tester.Tester;

interface ILoIntegers {
	boolean checkRequirementsA();

	// Suggested by ChatGPT
	boolean checkRequirementsHelperA(boolean thereIsAnEvenNumber, boolean thereIsAPositiveOddNumber,
			boolean thereIsANumberBetween5and10);

	boolean checkRequirementsB();

	boolean checkRequirementsHelperB(boolean thereIsAnEvenNumber, boolean thereIsAPositiveOddNumber,
			boolean thereIsANumberBetween5and10, int count);
}

class ConsLoIntegers implements ILoIntegers {
	int first;
	ILoIntegers rest;

	ConsLoIntegers(int first, ILoIntegers rest) {
		this.first = first;
		this.rest = rest;
	}

	public boolean checkRequirementsA() {
		return checkRequirementsHelperA(false, false, false);
	}

	@Override
	public boolean checkRequirementsHelperA(boolean thereIsAnEvenNumber, boolean thereIsAPositiveOddNumber,
			boolean thereIsANumberBetween5and10) {
		// Suggested by ChatGPT
		if (thereIsAnEvenNumber && thereIsAPositiveOddNumber && thereIsANumberBetween5and10) {
			return true;
		} else {
			thereIsAnEvenNumber = thereIsAnEvenNumber || first % 2 == 0;
			thereIsAPositiveOddNumber = thereIsAPositiveOddNumber || (first % 2 != 0 && first > 0);
			thereIsANumberBetween5and10 = thereIsANumberBetween5and10 || (first >= 5 && first <= 10);
			return rest.checkRequirementsHelperA(thereIsAnEvenNumber, thereIsAPositiveOddNumber,
					thereIsANumberBetween5and10);
		}
	}

	public boolean checkRequirementsB() {
		return checkRequirementsHelperB(false, false, false, 0);
	}

	public boolean checkRequirementsHelperB(boolean thereIsAnEvenNumber, boolean thereIsAPositiveOddNumber,
			boolean thereIsANumberBetween5and10, int count) {
		// Suggested by ChatGPT
		if (thereIsAnEvenNumber && thereIsAPositiveOddNumber && thereIsANumberBetween5and10) {
			return true;
		} else {
			thereIsAnEvenNumber = thereIsAnEvenNumber || first % 2 == 0;
			thereIsAPositiveOddNumber = thereIsAPositiveOddNumber || (first % 2 != 0 && first > 0);
			thereIsANumberBetween5and10 = thereIsANumberBetween5and10 || (first >= 5 && first <= 10);
			return rest.checkRequirementsHelperB(thereIsAnEvenNumber, thereIsAPositiveOddNumber,
					thereIsANumberBetween5and10, count++);
		}
	}
}

class EmptyLoIntegers implements ILoIntegers {
	public boolean checkRequirementsA() {
		return false;
	}

	@Override
	public boolean checkRequirementsHelperA(boolean thereIsAnEvenNumber, boolean thereIsAPositiveOddNumber,
			boolean thereIsANumberBetween5and10) {
		return thereIsAnEvenNumber && thereIsAPositiveOddNumber && thereIsANumberBetween5and10;
	}

	public boolean checkRequirementsB() {
		return false;
	}

	@Override
	public boolean checkRequirementsHelperB(boolean thereIsAnEvenNumber, boolean thereIsAPositiveOddNumber,
			boolean thereIsANumberBetween5and10, int count) {
		return thereIsAnEvenNumber && thereIsAPositiveOddNumber && thereIsANumberBetween5and10 && count >= 3;
	}
}

class ExamplesILoIntegers {
	ILoIntegers eli = new EmptyLoIntegers();
	ILoIntegers list1 = new ConsLoIntegers(6, new ConsLoIntegers(5, eli));
	ILoIntegers list2 = new ConsLoIntegers(4, new ConsLoIntegers(3, eli));
	ILoIntegers list3 = new ConsLoIntegers(6, new ConsLoIntegers(5, new ConsLoIntegers(6, eli)));

	boolean testCheckRequirementsA(Tester t) {
		return t.checkExpect(list1.checkRequirementsA(), true) && t.checkExpect(list2.checkRequirementsA(), false);
	}

	boolean testCheckRequirementsB(Tester t) {
		return t.checkExpect(list1.checkRequirementsB(), false) && t.checkExpect(list3.checkRequirementsB(), true);
	}
}