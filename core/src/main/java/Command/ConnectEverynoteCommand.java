package Command;

import Command.Interface.IStorageCommand;
import Domain.Root;

public class ConnectEverynoteCommand implements IStorageCommand {
    @Override
    public StorageCommandType getType() {
        return StorageCommandType.ConnectEverynote;
    }

    @Override
    public void setAttributes(String[] attributes) {

    }

    private Root root;

    public Root getRoot(){
        return root;
    }

    public void setRoot(Root root){
        this.root = root;
    }
}
