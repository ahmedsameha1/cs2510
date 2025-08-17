import tester.Tester;

interface ILoIntegers {
	boolean checkRequirements();

	// Suggested by ChatGPT
	boolean checkRequirementsHelper(boolean thereIsAnEvenNumber, boolean thereIsAPositiveOddNumber,
			boolean thereIsANumberBetween5and10);
}

class ConsLoIntegers implements ILoIntegers {
	int first;
	ILoIntegers rest;

	ConsLoIntegers(int first, ILoIntegers rest) {
		this.first = first;
		this.rest = rest;
	}

	public boolean checkRequirements() {
		return checkRequirementsHelper(false, false, false);
	}

	@Override
	public boolean checkRequirementsHelper(boolean thereIsAnEvenNumber, boolean thereIsAPositiveOddNumber,
			boolean thereIsANumberBetween5and10) {
		// Suggested by ChatGPT
		if (thereIsAnEvenNumber && thereIsAPositiveOddNumber && thereIsANumberBetween5and10) {
			return true;
		} else {
			thereIsAnEvenNumber = thereIsAnEvenNumber || first % 2 == 0;
			thereIsAPositiveOddNumber = thereIsAPositiveOddNumber || (first % 2 != 0 && first > 0);
			thereIsANumberBetween5and10 = thereIsANumberBetween5and10 || (first >= 5 && first <= 10);
			return (thereIsAnEvenNumber && thereIsAPositiveOddNumber && thereIsANumberBetween5and10)
					|| rest.checkRequirementsHelper(thereIsAnEvenNumber, thereIsAPositiveOddNumber,
							thereIsANumberBetween5and10);
		}
	}
}

class EmptyLoIntegers implements ILoIntegers {
	public boolean checkRequirements() {
		return false;
	}

	@Override
	public boolean checkRequirementsHelper(boolean thereIsAnEvenNumber, boolean thereIsAPositiveOddNumber,
			boolean thereIsANumberBetween5and10) {
		return false;
	}
}

class ExamplesILoIntegers {
	ILoIntegers eli = new EmptyLoIntegers();
	ILoIntegers list1 = new ConsLoIntegers(6, new ConsLoIntegers(5, eli));
	ILoIntegers list2 = new ConsLoIntegers(4, new ConsLoIntegers(3, eli));

	boolean testCheckRequirements(Tester t) {
		return t.checkExpect(list1.checkRequirements(), true) && t.checkExpect(list2.checkRequirements(), false);
	}
}