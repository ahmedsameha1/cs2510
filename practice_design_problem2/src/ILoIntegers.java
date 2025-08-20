import tester.Tester;

interface ILoIntegers {
	boolean checkRequirementsA();

	// Suggested by ChatGPT
	boolean checkRequirementsHelperA(boolean thereIsAnEvenNumber, boolean thereIsAPositiveOddNumber,
			boolean thereIsANumberBetween5and10);

	boolean checkRequirementsB();

	boolean checkRequirementsHelperB(boolean thereIsAnEvenNumber, boolean thereIsAPositiveOddNumber,
			boolean thereIsANumberBetween5and10);

	boolean checkRequirementsC();

	boolean checkRequirementsHelperC(boolean thereIsAnEvenNumber, boolean thereIsAPositiveOddNumber,
			boolean thereIsANumberBetween5and10);

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
		return checkRequirementsHelperB(false, false, false);
	}

	public boolean checkRequirementsHelperB(boolean thereIsAnEvenNumber, boolean thereIsAPositiveOddNumber,
			boolean thereIsANumberBetween5and10) {
		if (thereIsAnEvenNumber && thereIsAPositiveOddNumber && thereIsANumberBetween5and10) {
			return true;
		} else {
			if (!thereIsAnEvenNumber && first % 2 == 0) {
				return rest.checkRequirementsHelperB(true, thereIsAPositiveOddNumber, thereIsANumberBetween5and10);
			}
			if (!thereIsAPositiveOddNumber && (first % 2 != 0 && first > 0)) {
				return rest.checkRequirementsHelperB(thereIsAnEvenNumber, true, thereIsANumberBetween5and10);
			}
			if ((first >= 5 && first <= 10)) {
				return rest.checkRequirementsHelperB(thereIsAnEvenNumber, thereIsAPositiveOddNumber, true);
			} else {
				return true;
			}
		}

	}

	public boolean checkRequirementsC() {
		return checkRequirementsHelperC(false, false, false);
	}

	public boolean checkRequirementsHelperC(boolean thereIsAnEvenNumber, boolean thereIsAPositiveOddNumber,
			boolean thereIsANumberBetween5and10) {
		if (thereIsAnEvenNumber && thereIsAPositiveOddNumber && thereIsANumberBetween5and10) {
			return false;
		} else {
			if (!thereIsAnEvenNumber && first % 2 == 0) {
				return rest.checkRequirementsHelperC(true, thereIsAPositiveOddNumber, thereIsANumberBetween5and10);
			}
			if (!thereIsAPositiveOddNumber && (first % 2 != 0 && first > 0)) {
				return rest.checkRequirementsHelperC(thereIsAnEvenNumber, true, thereIsANumberBetween5and10);
			}
			if ((first >= 5 && first <= 10)) {
				return rest.checkRequirementsHelperC(thereIsAnEvenNumber, thereIsAPositiveOddNumber, true);
			} else {
				return false;
			}
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
			boolean thereIsANumberBetween5and10) {
		return thereIsAnEvenNumber && thereIsAPositiveOddNumber && thereIsANumberBetween5and10;
	}

	public boolean checkRequirementsC() {
		return false;
	}

	@Override
	public boolean checkRequirementsHelperC(boolean thereIsAnEvenNumber, boolean thereIsAPositiveOddNumber,
			boolean thereIsANumberBetween5and10) {
		return thereIsAnEvenNumber && thereIsAPositiveOddNumber && thereIsANumberBetween5and10;
	}
}

class ExamplesILoIntegers {
	ILoIntegers eli = new EmptyLoIntegers();
	ILoIntegers list1 = new ConsLoIntegers(6, new ConsLoIntegers(5, eli));
	ILoIntegers list2 = new ConsLoIntegers(4, new ConsLoIntegers(3, eli));
	ILoIntegers list3 = new ConsLoIntegers(6, new ConsLoIntegers(5, new ConsLoIntegers(6, eli)));
	ILoIntegers list4 = new ConsLoIntegers(6,
			new ConsLoIntegers(5, new ConsLoIntegers(42, new ConsLoIntegers(6, eli))));
	ILoIntegers list5 = new ConsLoIntegers(42,
			new ConsLoIntegers(5, new ConsLoIntegers(6, new ConsLoIntegers(6, eli))));
	ILoIntegers list6 = new ConsLoIntegers(6,
			new ConsLoIntegers(5, new ConsLoIntegers(6, new ConsLoIntegers(42, eli))));
	ILoIntegers list7 = new ConsLoIntegers(8,
			new ConsLoIntegers(5, new ConsLoIntegers(6, new ConsLoIntegers(42, eli))));

	boolean testCheckRequirementsA(Tester t) {
		return t.checkExpect(list1.checkRequirementsA(), true) && t.checkExpect(list2.checkRequirementsA(), false);
	}

	boolean testCheckRequirementsB(Tester t) {
		return t.checkExpect(list1.checkRequirementsB(), false) && t.checkExpect(list3.checkRequirementsB(), true)
				&& t.checkExpect(list4.checkRequirementsB(), true) && t.checkExpect(list5.checkRequirementsB(), true)
				&& t.checkExpect(list6.checkRequirementsB(), true) && t.checkExpect(list7.checkRequirementsB(), true);
	}

	boolean testCheckRequirementsC(Tester t) {
		return t.checkExpect(list3.checkRequirementsC(), true) && t.checkExpect(list4.checkRequirementsC(), false)
				&& t.checkExpect(list5.checkRequirementsC(), false) && t.checkExpect(list6.checkRequirementsC(), false)
				&& t.checkExpect(list7.checkRequirementsC(), false);
	}
}