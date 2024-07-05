function fillMissingData(data) {
  // Ensure data is sorted by timestamp (if not already)
  data.sort((a, b) => new Date(a.timestamp) - new Date(b.timestamp));

  // Create a new array to store filled data
  let filledData = [];

  // Track timestamps to filter duplicates
  let timestampSet = new Set();

  // Iterate through data points
  for (let i = 0; i < data.length; i++) {
      let currentItem = data[i];
      let currentTimestamp = new Date(currentItem.timestamp);

      // Check if timestamp already exists in set (duplicate)
      if (!timestampSet.has(currentTimestamp.getTime())) {
          filledData.push(currentItem);
          timestampSet.add(currentTimestamp.getTime());
      }
  }

  return filledData;
}

// Exporting function to be used in other scripts if needed
window.fillMissingData = fillMissingData;
