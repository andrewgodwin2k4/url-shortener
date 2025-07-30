const form = document.getElementById("urlForm");
const resultDiv = document.getElementById("result");
  
form.addEventListener("submit", async (e) => {
    e.preventDefault();
  
    const longUrl = document.getElementById("longUrl").value;
    const alias = document.getElementById("alias").value;
  
    const response = await fetch("http://localhost:8080/shortenUrl", {
        method: "POST",
        headers: {
           "Content-Type": "application/json"
        },
        body: JSON.stringify({ longUrl, alias })
    });
  
    const resultText = await response.text();
  
    if (!response.ok) {
        resultDiv.innerHTML = `<p style="color: red;"><strong> Error:</strong> ${resultText}</p>`;
    } 
    else {
        resultDiv.innerHTML = `<p><strong>Short URL:</strong> <a href="${resultText}" target="_blank">${resultText}</a></p>`;
    }
});
