import { Box, Button, CircularProgress, Container, FormControl, InputLabel, MenuItem, Select, TextField, Typography } from '@mui/material'
import { useState } from 'react';
import axios from "axios";
function App() {
  const [emailContent, setEmailContent] = useState("");
  const [tone, setTone] = useState("");
  const [remarks,setRemarks] = useState("");
  const [generatedReply, setGeneratedReply] = useState("");
  const [loading, setLoading] = useState(false);


  const handleSubmit = async () => {
    setLoading(_ => true);
    try {
      const response = await axios.post("http://localhost:8081/api/email/generate", {
        emailContent,
        tone,
        remarks
      });
      setGeneratedReply(typeof response.data === 'string' ?
        response.data : JSON.stringify(response.data)
      );
    } catch (error) {
      console.log("error");
      console.log(error);
    } finally {
      setLoading(_ => false);
    }
  };
  return (
    <Container maxWidth="md"
      sx={{
        py: 2,
        bgcolor: "#ffffffff",

      }}>
      <Typography
        variant='h3'
        component="h1"
        gutterBottom
        sx={{ display: { xs: "none", sm: "block" } }}>
        Email Reply Generator
      </Typography>
      <Typography
        variant='h5'
        component="h1"
        gutterBottom
        sx={{ display: { xs: "block", sm: "none" } }}>
        Email Reply Generator
      </Typography>

      <Box >
        <TextField
          fullWidth
          multiline
          rows={6}
          variant="outlined"
          label="Original Email Content"
          placeholder="Paste the email here"
          sx={{ marginBottom: "20px" }}
          value={emailContent || ""}
          onChange={(e) => setEmailContent(e.target.value)}
        ></TextField>

        <FormControl fullWidth sx={{ marginBottom: "20px" }} >
          <InputLabel >Tone (Optional)</InputLabel>
          <Select
            value={tone}
            label="Tone (Optional)"
            placeholder="Tone"
            onChange={(e) => setTone(e.target.value)}
          >
            <MenuItem value="">None</MenuItem>
            <MenuItem value="Professional">Professional</MenuItem>
            <MenuItem value="Respectful">Respectful</MenuItem>
            <MenuItem value="Casual">Casual</MenuItem>
            <MenuItem value="Friendly">Friendly</MenuItem>
          </Select>
        </FormControl>
      </Box>

      <Box >
        <TextField
          fullWidth
          multiline
          rows={2}
          variant="outlined"
          label="Remarks (Optional)"
          placeholder='Write your remark for the reply'
          sx={{ marginBottom: "20px" }}
          value={remarks || ""}
          onChange={(e)=> setRemarks(e.target.value)}
        ></TextField>

      </Box>
        <Button
          onClick={handleSubmit}
          variant="contained"
          size='small'
          disabled={!emailContent || loading}
          sx={{ marginBottom: "20px" }} >
          {loading ? <CircularProgress size={24} /> : "Generate Reply"}
        </Button>


      <Box >
        <TextField
          fullWidth
          multiline
          rows={6}
          variant="outlined"
          label="Email Reply"
          sx={{ marginBottom: "20px" }}
          value={generatedReply || ""}
          InputProps={{
            readOnly: true
          }}
        ></TextField>

        <Button
          variant='outlined'
          onClick={() => navigator.clipboard.writeText(generatedReply)}
          disabled={!generatedReply || loading} >
          Copy to ClipBoard
        </Button>
      </Box>
    </Container>
  );

}

export default App
