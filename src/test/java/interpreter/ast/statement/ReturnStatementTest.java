package interpreter.ast.statement;

import com.github.konstantinevashalomidze.interpreter.ast.expression.Expression;
import com.github.konstantinevashalomidze.interpreter.ast.statement.ReturnStatement;
import com.github.konstantinevashalomidze.interpreter.token.Token;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReturnStatementTest {

    private Token token;
    private Expression value;

    @BeforeEach
    void setUp() {
        token = mock(Token.class);
        value = mock(Expression.class);
    }

    @AfterEach
    void tearDown() {
        token = null;
        value = null;
    }

    @Test
    void shouldCreateReturnStatementWithTokenAndValue() {
        // Act
        ReturnStatement returnStatement = new ReturnStatement(token, value);

        // Assert
        assertEquals(token, returnStatement.getToken());
        assertEquals(value, returnStatement.getValue());
    }

    @Test
    void shouldCreateReturnStatementWithTokenOnly() {
        // Act
        ReturnStatement returnStatement = new ReturnStatement(token);

        // Assert
        assertEquals(token, returnStatement.getToken());
        assertNull(returnStatement.getValue());
    }

    @Test
    void shouldCreateDefaultReturnStatement() {
        // Act
        ReturnStatement returnStatement = new ReturnStatement();

        // Assert
        assertNull(returnStatement.getToken());
        assertNull(returnStatement.getValue());
    }

    @Test
    void shouldSetTokenSuccessfully() {
        // Arrange
        ReturnStatement returnStatement = new ReturnStatement();

        // Act
        returnStatement.setToken(token);

        // Assert
        assertEquals(token, returnStatement.getToken());
    }

    @Test
    void shouldSetValueSuccessfully() {
        // Arrange
        ReturnStatement returnStatement = new ReturnStatement();

        // Act
        returnStatement.setValue(value);

        // Assert
        assertEquals(value, returnStatement.getValue());
    }

    @Test
    void shouldReturnLiteralFromToken() {
        // Act
        when(token.literal()).thenReturn("return");
        ReturnStatement returnStatement = new ReturnStatement(token);

        // Assert
        assertEquals("return", returnStatement.literal());
    }

    @Test
    void shouldReturnStringRepresentationWithTokenAndValue() {
        // Arrange
        when(token.literal()).thenReturn("return");
        when(value.toString()).thenReturn("MockExpression");
        ReturnStatement returnStatement = new ReturnStatement(token, value);

        // Act
        String result = returnStatement.toString();

        // Assert
        String expected = "ReturnStatement (return)\n  |- MockExpression\n";
        assertEquals(expected, result);
    }

    @Test
    void shouldReturnStringRepresentationWithTokenOnly() {
        // Act
        when(token.literal()).thenReturn("return");
        ReturnStatement returnStatement = new ReturnStatement(token);

        // Assert
        String expected = "ReturnStatement (return)\n";
        assertEquals(expected, returnStatement.toString());
    }

    @Test
    void shouldReturnStringRepresentationWithNoTokenOrValue() {
        // Act
        ReturnStatement returnStatement = new ReturnStatement();

        // Assert
        String expected = "ReturnStatement\n";
        assertEquals(expected, returnStatement.toString());
    }

    @Test
    void shouldReturnStringRepresentationWithValueOnly() {
    // Arrange
    when(value.toString()).thenReturn("MockExpression");

    ReturnStatement returnStatement = new ReturnStatement();
    returnStatement.setValue(value); 

    // Act
    String result = returnStatement.toString();

    // Assert
    String expected = "ReturnStatement\n  |- MockExpression\n";
    assertEquals(expected, result);
    }

}