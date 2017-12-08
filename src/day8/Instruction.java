package day8;

public class Instruction {
    public final String registerThatWillChange;
    public final String instruction;
    public final int valueThatWillBeAddedOrSubtracted;
    public final String operationRegister;
    public final String operation;
    public final int operationValue;

    public Instruction(
            final String registerThatWillChange,
            final String instruction,
            int valueThatWillBeAddedOrSubtracted,
            String operationRegister,
            String operation,
            int operationValue
    ) {
        this.registerThatWillChange = registerThatWillChange;
        this.instruction = instruction;
        this.valueThatWillBeAddedOrSubtracted = valueThatWillBeAddedOrSubtracted;
        this.operationRegister = operationRegister;
        this.operation = operation;
        this.operationValue = operationValue;
    }
}
