package englishclass.controller;

import englishclass.model.ModelAcess;

public abstract class AbstractController {

    private ModelAcess model;

    public AbstractController(ModelAcess model) {
        this.model = model;
    }

    protected ModelAcess getModel() {
        return model;
    }



}
