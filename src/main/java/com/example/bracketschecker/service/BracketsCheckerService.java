package com.example.bracketschecker.service;

import com.example.bracketschecker.dto.RequestDto;
import com.example.bracketschecker.dto.ResponseDto;
import org.springframework.stereotype.Service;

import java.util.Stack;

import static com.example.bracketschecker.dto.ResponseDto.createFalseResponse;
import static com.example.bracketschecker.dto.ResponseDto.createTrueResponse;

@Service
public class BracketsCheckerService {

    private final static Stack<Character> BRACKETS_STACK = new Stack<>();
    private final static char TEXT_PRESENSE_SYMBOL = 't';
    private final static char OPENING_BRACKET = '(';
    private final static char CLOSING_BRACKET = ')';


    public ResponseDto checkBrackets(RequestDto requestDto) {
        BRACKETS_STACK.clear();
        String text = requestDto.getText();

        if (text == null) {
            return createFalseResponse();
        }

        for (Character character : text.toCharArray()) {
            if (checkForMistakesIsPositive(character)){
                return createFalseResponse();
            }
            proceedWithStack(character);
        }

        return getResult();
    }

    private boolean checkForMistakesIsPositive(Character character) {
        return isClosedBracketWithoutAPair(character) || isClosedBracketAndNoTextAfterOpenedOne(character);
    }

    private boolean isClosedBracketWithoutAPair(Character letter) {
        return letter.equals(')') && BRACKETS_STACK.empty();
    }

    private boolean isClosedBracketAndNoTextAfterOpenedOne(Character letter) {
        return letter.equals(')') && BRACKETS_STACK.peek() == '(';
    }

    private void proceedWithStack(Character character) {
        if (character.equals(OPENING_BRACKET)) {
            addToTheStack(character);
        } else if (character.equals(CLOSING_BRACKET)) {
            popLastTwoCharacters();
        } else if (noTextPresenseAfterOpeningBracket()) {
            addTextPresenseSymbolToTheStack();
        }
    }

    private void addToTheStack(Character character) {
        BRACKETS_STACK.add(character);
    }

    private void popLastTwoCharacters() {
        BRACKETS_STACK.pop();
        BRACKETS_STACK.pop();
    }

    private boolean noTextPresenseAfterOpeningBracket() {
        return !BRACKETS_STACK.empty() && BRACKETS_STACK.peek() == '(';
    }

    private void addTextPresenseSymbolToTheStack() {
        BRACKETS_STACK.add(TEXT_PRESENSE_SYMBOL);
    }

    private static ResponseDto getResult() {
        if (BRACKETS_STACK.empty()) {
            return createTrueResponse();
        }
        return createFalseResponse();
    }
}
