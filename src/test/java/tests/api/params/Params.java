package tests.api.params;

public class Params {

    public String NewBoardId;
    public String NewListId;

    public Params(){
        this.NewBoardId = NewBoardId;
        this.NewListId = NewListId;
    }

    public String getNewListId() {
        return NewListId;
    }

    public void setNewListId(String newListId) {
        NewListId = newListId;
    }

    public String getNewBoardId() {
        return NewBoardId;
    }

    public void setNewBoardId(String newBoardId) {
        NewBoardId = newBoardId;
    }
}
