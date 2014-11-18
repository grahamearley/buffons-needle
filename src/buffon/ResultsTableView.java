package buffon;

import javafx.scene.control.TableView;

/**
 * Created by grahamearley on 11/12/14.
 *
 * https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
 */
public class ResultsTableView {
    private TableView table;

    public ResultsTableView() {
        table = new TableView();
        table.setEditable(true);

    }
}
