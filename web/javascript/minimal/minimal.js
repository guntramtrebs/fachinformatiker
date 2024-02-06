class HtmlOutput {

    constructor(elementId) {
        this.elementId = elementId;
        this.outputElement = document.getElementById(this.elementId);
    }

    print(line) {
        this.outputElement.appendChild(
            document.createTextNode(line)
        );
        this.outputElement.appendChild(
            document.createElement("br")
        );
    }

    clear() {
        this.outputElement.innerHTML = "";
    }
}

htmlOutput = new HtmlOutput("htmloutput");

function clearOutput() {
    htmlOutput.clear();
}