package com.example.bracketschecker;

import com.example.bracketschecker.dto.RequestDto;
import com.example.bracketschecker.dto.ResponseDto;
import com.example.bracketschecker.service.BracketsCheckerService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BracketsCheckerApplicationTests {

	@Autowired
	private BracketsCheckerService bracketsCheckerService;

	@ParameterizedTest
	@MethodSource("provideStringsForIsBlank")
	void contextLoads(String text, boolean expected) {
		ResponseDto actual = bracketsCheckerService.checkBrackets(new RequestDto(text));

		assertEquals(expected, actual.isCorrect());
	}

	private static Stream<Arguments> provideStringsForIsBlank() {
		return Stream.of(
				Arguments.of("abv", true),
				Arguments.of("abv()", false),
				Arguments.of("(b)", true),
				Arguments.of("((b))", true),
				Arguments.of(null, false),
				Arguments.of("", true),
				Arguments.of("     ", true)
				);
	}

}
