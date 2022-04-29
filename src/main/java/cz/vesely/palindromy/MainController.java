package cz.vesely.palindromy;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class MainController {
    @FXML
    private TextArea vstupniText;

    @FXML
    private TextArea output;

    @FXML
    private CheckBox ignoreDiacritics;

    @FXML
    private CheckBox checkAutomatically;

    @FXML
    private CheckBox checkSize;

    private final PalindromFinder finder;

    public MainController() {
        finder = new PalindromFinder();
    }

    @FXML
    protected void input() {
        if (checkAutomatically.isSelected()) {
            runCheck();
        }
    }

    @FXML
    protected void onCheckButtonClick() {
        runCheck();
    }

    @FXML
    protected void onClickCheckAutomatically() {
        runCheck();
    }

    @FXML
    protected void onClickCheckSize() {
        runCheck();
    }

    @FXML
    protected void onClickIgnoreDiacritics() {
        finder.setIgnoreDiacritics(ignoreDiacritics.isSelected());
        if (checkAutomatically.isSelected()) {
            runCheck();
        }
    }

    private void runCheck() {
        finder.setIgnoreDiacritics(ignoreDiacritics.isSelected());
        finder.setIgnoreSize(checkSize.isSelected());
        if (vstupniText.getText().isEmpty()) {
            return;
        }
        String text = finder.findAll(vstupniText.getText());
        output.setText(text);
    }
}