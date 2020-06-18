package tw.racket.racketclient;

public class JumpToDefinition {
    private int start;
    private int end;
    private String name;
    private String filename;
    // empty submodules means defined in top-level module
    private String[] submodules;
}
