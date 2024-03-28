#!/bin/bash

# List of Java files to include in the zip
java_files="SeamCarver.java"

# Name of the zip file
zip_file="seam.zip"

# Temporary directory to store modified files
temp_dir=$(mktemp -d)

# Save the first line of each Java file and remove it temporarily
for file in $java_files; do
    # Save the first line
    first_line=$(head -n 1 "$file")
    # Remove the first line and store the modified file in the temporary directory
    tail -n +2 "$file" > "$temp_dir/$file"
done

# Zip the modified Java files
zip -r "$zip_file" "$temp_dir"/*

# Restore the first line of each Java file
for file in $java_files; do
    # Restore the first line from the saved value
    echo "$first_line" | cat - "$temp_dir/$file" > "$temp_dir/$file.tmp"
    # Overwrite the modified file with the restored first line
    mv "$temp_dir/$file.tmp" "$temp_dir/$file"
done

# Clean up temporary directory
rm -rf "$temp_dir"

echo "Java files zipped into $zip_file"
